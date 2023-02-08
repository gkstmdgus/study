package com.example.helloboot;


import config.MySpringBootApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@MySpringBootApplication
public class HelloBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloBootApplication.class, args);
    }
}


