package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.JwtTokenService;
import com.flashcardsapi.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    //todo: put refresh token in http only cookies
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User authenticationUser = (User) authentication.getPrincipal();
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", tokenService.generateToken(authenticationUser));
        tokens.put("refreshToken", tokenService.generateRefreshToken(authenticationUser));
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        ResponseEntity.BodyBuilder unauthorizedBodyBuilder = ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        String notValidTokenMessage = "Token isn't valid";
        Map<String, String> response;
        if (refreshToken == null) {
            response = new HashMap<>();
            response.put("message", "Token isn't present!");
            return unauthorizedBodyBuilder.body(response);
        }
        response = new HashMap<>();
        response.put("message", notValidTokenMessage);
        if (!tokenService.isTokenValid(refreshToken)) {
            return unauthorizedBodyBuilder.body(response);
        }
        User user = userService.getUserByToken(refreshToken);
        if (user == null) {
            return unauthorizedBodyBuilder.body(response);
        }
        response = tokenService.generateTokens(user);
        return ResponseEntity.ok(response);
    }

}
