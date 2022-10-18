package com.chornobuk.flashcardsapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderBeanConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
//        todo: change leven of encryption
        return new BCryptPasswordEncoder();
    }
}
