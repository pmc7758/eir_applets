package com.eirapplets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EirAppletsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EirAppletsApplication.class, args);
    }

}
