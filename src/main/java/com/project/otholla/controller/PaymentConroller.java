package com.project.otholla.controller;

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
        String invalid = "{\"code\":\"INVALID_USER\",\"message\":null}";
        if (!"ncsoft".equalsIgnoreCase(requestwebhook.getUsers().getId())){
            return new ResponseEntity(invalid,HttpStatus.NOT_FOUND);
        }
        String token = request.getHeader("Authorization");
        if("ncsoft".equalsIgnoreCase(requestwebhook.getUsers().getId())){
            return new ResponseEntity(HttpStatus.OK);
        }

        String eddd = "{\"code\":\"INVALID_SIGNATURE\",\"message\":null}";
        return new ResponseEntity(eddd,HttpStatus.BAD_REQUEST);
    }

}
