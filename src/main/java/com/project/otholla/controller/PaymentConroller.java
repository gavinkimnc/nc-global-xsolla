package com.project.otholla.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.project.otholla.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class PaymentConroller {

    @Autowired
    PaymentService paymentService;

    String secretKey = "U-CWaZHflG80f5KKMn__B";

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
    public ResponseEntity webhook(@RequestBody WebhookReq requestwebhook, HttpServletRequest request, HttpServletResponse response) {

        String invalidUser = "{\"code\":\"INVALID_USER\",\"message\":null}";
        String invalidSignature = "{\"code\":\"INVALID_SIGNATURE\",\"message\":null}";

        String signature = request.getHeader("Signature ");

        log.info("{}", requestwebhook.toString());

        if (!validId(requestwebhook.getUsers().getId())){
            return new ResponseEntity(invalidUser,HttpStatus.NOT_FOUND);
        } else if(!validSignatre(requestwebhook.toString(), signature)){
            return new ResponseEntity(invalidSignature,HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    private boolean validId(String id){
        return "ncsoft".equalsIgnoreCase(id);
    }

    private boolean validSignatre(String body, String hash){
        String signature = Hashing.sha1().hashString(body + secretKey , Charsets.UTF_8 ).toString();
        return hash.equalsIgnoreCase(signature);
    }

}
