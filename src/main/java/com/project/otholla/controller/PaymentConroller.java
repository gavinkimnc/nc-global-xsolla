package com.project.otholla.controller;

import com.project.otholla.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
public class PaymentConroller {

    @Autowired
    PaymentService paymentService;

    String secretKey = "45107c6b-9dac-463e-add9-46d44ace14bc";
    String invalidUser = "{ \"error\":{\"code\":\"INVALID_USER\",\"message\":null}}";
    String invalidSignature = "{ \"error\":{\"code\":\"INVALID_SIGNATURE\",\"message\":null}}";

    @GetMapping("oshop")
    public String oshop() {
        return "oshop";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/payment")
    public String payment(
            @RequestParam String myname,
            @RequestParam String currency,
            @RequestParam String amount,
            @RequestParam String country
    ) {

        String accessToken = paymentService.token(myname, currency, amount, country);
        return "redirect:" + String.format("https://sandbox-secure.xsolla.com/paystation3/?access_token=%s", accessToken);
    }

    @PostMapping("/webhook")
    @ResponseBody
    public ResponseEntity webhook(@RequestBody WebhookReq requestwebhook, HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("requestwebhook : {}", requestwebhook);
        String signature = request.getHeader("Authorization");
        String body = readBody(request);
        log.info("signature: {}, body: {}", signature, body);
        if("user_validation".equalsIgnoreCase(requestwebhook.getNotificationType())) {
            return validId(requestwebhook.getUsers().getId());
        }

        if("payment".equalsIgnoreCase(requestwebhook.getNotificationType())) {
            return paymentValidSignature(body,signature);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    private ResponseEntity validId(String id){
        if("ncsoft".equalsIgnoreCase(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(invalidUser,HttpStatus.NOT_FOUND);
    }

    private ResponseEntity paymentValidSignature(String body, String signature){
        try {
            String hash = sha1(body+ secretKey);
            log.info(">>>> hashed: {}", hash);
            if(hash.equalsIgnoreCase(signature)) {
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(">>>>> : {}", e.getMessage());
        }
        return new ResponseEntity(invalidSignature, HttpStatus.BAD_REQUEST);
    }

    private boolean validSignature(String body, String signature) {

        try {
            String hash = sha1(body+ secretKey);
            log.info(">>>> hashed: {}", hash);
            return hash.equalsIgnoreCase(signature);
        } catch (NoSuchAlgorithmException e) {
            log.error(">>>>> : {}", e.getMessage());
            return false;
        }
    }


    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }


    public static String readBody(HttpServletRequest request) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        return builder.toString();
    }

}
