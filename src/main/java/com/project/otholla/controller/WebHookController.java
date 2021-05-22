package com.project.otholla.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.otholla.controller.request.WebHookReq2;
import com.project.otholla.service.WebHookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class WebHookController {

    @Autowired
    WebHookService webHookService;

    private String INVALID_USER = "{ \"error\":{\"code\":\"INVALID_USER\",\"message\":null}}";
    private String INVALID_SIGNATURE = "{ \"error\":{\"code\":\"INVALID_SIGNATURE\",\"message\":null}}";

    @GetMapping("/success")
    public String webhook(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @PostMapping("/webhook")
    @ResponseBody
    public ResponseEntity webhook(@RequestBody String body, @RequestHeader(value="Authorization") String authorization) throws JsonProcessingException {

        WebHookReq2 webHookReq = new ObjectMapper().readValue(body, WebHookReq2.class);

        log.info("body : {}", body);
        log.info("webHookReq : {}", webHookReq);
        log.info("Authorization: {}", authorization);

        String notificationType = webHookReq.getNotificationType();

        if ("user_validation".equalsIgnoreCase(notificationType)) {
            String userId = webHookReq.getUser().getId();
            return webHookService.validId(userId) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(INVALID_USER, HttpStatus.NOT_FOUND);
        }

        if ("payment".equalsIgnoreCase(notificationType)) {
            return webHookService.validSignaturePayment(body, authorization) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(INVALID_SIGNATURE, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);

    }

}