package com.example.basicproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Transactional
public class BasicProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicProjectApplication.class, args);
    }

}
