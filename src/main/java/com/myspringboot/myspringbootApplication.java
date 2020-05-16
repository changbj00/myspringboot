package com.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class myspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(myspringbootApplication.class, args);
    }

}
