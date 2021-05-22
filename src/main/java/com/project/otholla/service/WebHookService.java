package com.project.otholla.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.otholla.controller.request.WebHookReq2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class WebHookService {

    private String secretKey = "U-CWaZHflG80f5KKMn__B";

    public boolean validId(String id) {
        return id.startsWith("ncsoft");
    }

    public boolean validSignaturePayment(String plainText, String signature) {

        try {

            String encoded = "Signature " + sha1(plainText + secretKey);

            log.info(">>>> encoded  \t: {}", encoded);
            log.info(">>>> signature\t: {}", signature);

            return encoded.equals(signature);

        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException : {}", e.getMessage());
        }

        return false;
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
}
