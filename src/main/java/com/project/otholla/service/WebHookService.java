package com.project.otholla.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
@Service
public class WebHookService {

    Map secretKey = Maps.newHashMap();

    private void init() {
        secretKey.put(132105, "U-CWaZHflG80f5KKMn__B");       //오벤져스
        secretKey.put(132058, "45107c6b-9dac-463e-add9-46d44ace14bc");//코벤져스
    }

    public boolean validId(String id) {
        return id.startsWith("ncsoft");
    }

    public boolean validSignature(String plainText, String signature, Integer projectId) {

        if (!secretKey.containsKey(projectId)) {
            return false;
        }

        try {
            String encoded = "Signature " + sha1(plainText + secretKey.get(projectId));
            return encoded.equals(signature);

        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException : {}", e.getMessage());
        }

        return false;

    }

    public boolean validSignature(String plainText, String signature) {
        return validSignature(plainText, signature, 132105);
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
