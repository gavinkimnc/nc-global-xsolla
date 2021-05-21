package com.project.otholla.controller;

import com.project.otholla.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/oshop/webhook")
    public String webhook(@RequestBody WebhookReq request) {
        String notiType = request.getNotificationType();
        return notiType;
    }
}
