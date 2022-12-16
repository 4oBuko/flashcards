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
    // todo: maybe I should to rename endpoinst for update set and tag
    // (instead of put method for tags/ and sets/ do put method for tags/tag and sets/set)
    // todo: add error response customization (I can send exceptions with message
    // without custom message object or maps)
//    todo: for custom json response (e.g status and data object) you can create builder
//      this builder will use string, string map to store a custom response.
}
