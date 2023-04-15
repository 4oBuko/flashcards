package com.flashcardsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashcardsapi.dtos.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.JwtTokenService;
import com.flashcardsapi.services.UserService;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//todo: should I use _path syntax for these methods?
//because they are post but don't change data
@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    private AuthenticationManager authenticationManager;

    private ObjectMapper mapper;

    private final JwtTokenService tokenService;
//todo: add handler for failed login and return text message
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody LoginDTO dto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User authenticationUser = (User) authentication.getPrincipal();
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", tokenService.generateToken(authenticationUser));
        response.addCookie(getRefreshTokenCookie(authenticationUser, dto.isStayLoggedIn()));
        return tokens;
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String notValidTokenMessage = "Token isn't valid";
        Cookie refreshToken = WebUtils.getCookie(request, "refreshToken");
        Map<String, String> responseBody;
        if (refreshToken == null) {
            responseBody = new HashMap<>();
            responseBody.put("message", "Token isn't present!");
            return ResponseEntity.badRequest().body(responseBody);
        }
        responseBody = new HashMap<>();
        responseBody.put("message", notValidTokenMessage);
        if (!tokenService.isTokenValid(refreshToken.getValue())) {
            return ResponseEntity.badRequest().body(responseBody);
        }
//        if user by id wasn't found error will be handled in exception handler
        User user = userService.getUserByToken(refreshToken.getValue());
        String token = tokenService.generateToken(user);
        responseBody = new HashMap<>();
        responseBody.put("token", token);
//        cookie age -1 means cookie will be deleted when the current session ends
        response.addCookie(getRefreshTokenCookie(user, refreshToken.getMaxAge() != -1));
        return ResponseEntity.ok(responseBody);
    }

    @ResponseBody
    @PostMapping("/logout")
    public String logoutUser(HttpServletResponse response) {
        Cookie emptyCookie = new Cookie("refreshToken", null);
        emptyCookie.setMaxAge(0);
        emptyCookie.setHttpOnly(true);
        response.addCookie(emptyCookie);
        return "Log out successfully";
    }

    private Cookie getRefreshTokenCookie(User user, boolean stayLoggedIn) {
        Cookie jwtRefreshTokenCookie = new Cookie("refreshToken", tokenService.generateRefreshToken(user));
        jwtRefreshTokenCookie.setHttpOnly(true);
        if (stayLoggedIn) {
            jwtRefreshTokenCookie.setMaxAge(182 * 24 * 60 * 60);
        }
        // jwtRefreshTokenCookie.setSecure(true);
        return jwtRefreshTokenCookie;
    }
}
