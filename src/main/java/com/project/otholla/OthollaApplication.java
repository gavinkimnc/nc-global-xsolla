package com.project.otholla;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class OthollaApplication {

    public static void main(String[] args) {

        log.info(" 프로젝트 - 오쏠라");
        SpringApplication.run(OthollaApplication.class, args);
    }

}
