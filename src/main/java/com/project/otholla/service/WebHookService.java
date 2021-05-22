package com.project.otholla.service;

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
        for (byte b : result) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
