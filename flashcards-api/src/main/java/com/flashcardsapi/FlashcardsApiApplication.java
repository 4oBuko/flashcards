package com.flashcardsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.flashcardsapi.config.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class FlashcardsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashcardsApiApplication.class, args);
    }
}
