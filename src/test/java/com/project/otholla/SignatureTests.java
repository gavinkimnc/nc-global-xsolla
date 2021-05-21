package com.project.otholla;

import com.project.otholla.controller.PaymentConroller;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;


@Slf4j
@SpringBootTest
public class SignatureTests {

    @Test
    public void test()  {

        try {
            String hashed = PaymentConroller.sha1("{\"notification_type\":\"user_validation\",\"settings\":{\"project_id\":132105,\"merchant_id\":202724},\"user\":{\"id\":\"ncsoft\"}}" + "U-CWaZHflG80f5KKMn__B");

            String signature = "79b75a4afc63e1b1f2f9e109d5877ea97f4c3117";

            log.info("{}", hashed);
            log.info("{}", signature.equals(hashed));
        }catch (Exception e) {

        }
    }
}
