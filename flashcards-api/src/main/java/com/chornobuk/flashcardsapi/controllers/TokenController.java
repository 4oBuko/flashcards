package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private static final Logger LOG = LoggerFactory.getLogger(TokenController.class);
    private final JwtTokenService tokenService;

    @PostMapping("/token")
    public String getToken(Authentication authentication) {
        LOG.debug("Token for user {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token: {}", token);
        return token;
    }
}
