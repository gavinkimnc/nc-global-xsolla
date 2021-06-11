package com.project.otholla.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
@Service
public class WebHookService {

    Map secretKey = Maps.newHashMap();

    @PostConstruct
    private void init() {
        secretKey.put("ovengers", "U-CWaZHflG80f5KKMn__B");       //오벤져스
        secretKey.put("covengers", "45107c6b-9dac-463e-add9-46d44ace14bc");//코벤져스
        log.info("initialize secretKey : {}", secretKey);
    }

    public boolean validId(String id) {
        return id.startsWith("ncsoft");
    }

    public boolean validSignature(String plainText, String signature, String projectName) {

        if (!secretKey.containsKey(projectName)) {
            log.error("NOT SUPPORT PROJECT {}", projectName);
            return false;
        }

        try {
            String encoded = "Signature " + sha1(plainText + secretKey.get(projectName));

            log.info(">>>> result   \t: {}", encoded.equals(signature));
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
