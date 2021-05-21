package com.project.otholla.controller;

import com.project.otholla.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("index")
    public String index() {

        log.info("index : {}" , "index");

        paymentService.authenticationSample();

        return "index";
    }

}