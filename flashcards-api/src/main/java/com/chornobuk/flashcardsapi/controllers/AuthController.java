package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.JwtTokenService;
import com.chornobuk.flashcardsapi.services.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    //    todo: add new controller for user (change user's data, etc.)
    private final static Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private UserService userService;

    private AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", tokenService.generateToken(authentication));
        tokens.put("refreshToken", tokenService.generateRefreshToken(authentication));
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshAccessToken(@RequestBody Map<String,String> body) {
//todo: if refresh token is valid generate new access and refresh tokens
//        return ResponseEntity.ok("todo");
        return ResponseEntity.ok(String.valueOf(tokenService.isTokenValid(body.get("refreshToken"))));
    }

}
