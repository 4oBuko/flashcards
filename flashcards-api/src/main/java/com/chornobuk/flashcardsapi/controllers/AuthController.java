package com.chornobuk.flashcardsapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<String> loginUser() {
        return ResponseEntity.ok("todo") ;
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshAccesstoken() {
        return ResponseEntity.ok("todo") ;
    }
}
