package com.chornobuk.flashcardsapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("languages")
public class LanguagesController {
    @GetMapping()
    public ResponseEntity<String> getAllLanguages() {
        return ResponseEntity.ok("todo") ;
    }
}
