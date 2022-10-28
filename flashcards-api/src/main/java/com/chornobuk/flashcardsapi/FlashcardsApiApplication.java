package com.chornobuk.flashcardsapi;

import com.chornobuk.flashcardsapi.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class FlashcardsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashcardsApiApplication.class, args);
    }
//    todo: for custom json response (e.g status and data object) you can create builder
//      this builder will use string, string map to store a custom response.
}
