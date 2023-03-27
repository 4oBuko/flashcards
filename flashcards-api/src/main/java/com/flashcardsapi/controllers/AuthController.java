package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    private AuthenticationManager authenticationManager;

    private final JwtTokenService tokenService;

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User user, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User authenticationUser = (User) authentication.getPrincipal();
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", tokenService.generateToken(authenticationUser));
        response.addCookie(getRefreshTokenCookie(authenticationUser));
        return tokens;
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshAccessToken(
            @CookieValue(name = "refreshToken") String refreshToken, HttpServletResponse response) {
        String notValidTokenMessage = "Token isn't valid";
        Map<String, String> responseBody;
        if (refreshToken == null) {
            responseBody = new HashMap<>();
            responseBody.put("message", "Token isn't present!");
            return ResponseEntity.badRequest().body(responseBody);
        }
        responseBody = new HashMap<>();
        responseBody.put("message", notValidTokenMessage);
        if (!tokenService.isTokenValid(refreshToken)) {
            return ResponseEntity.badRequest().body(responseBody);
        }
//        if user by id wasn't found error will be handled in exception handler
        User user = userService.getUserByToken(refreshToken);
        String token = tokenService.generateToken(user);
        responseBody = new HashMap<>();
        responseBody.put("token", token);
        response.addCookie(getRefreshTokenCookie(user));
        return ResponseEntity.ok(responseBody);
    }

    private Cookie getRefreshTokenCookie(User user) {
        Cookie jwtRefreshTokenCookie = new Cookie("refreshToken", tokenService.generateRefreshToken(user));
        jwtRefreshTokenCookie.setHttpOnly(true);
        jwtRefreshTokenCookie.setMaxAge(182 * 24 * 60 * 60);
        // jwtRefreshTokenCookie.setSecure(true);
        return jwtRefreshTokenCookie;
    }
}
