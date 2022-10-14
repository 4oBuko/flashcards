package com.chornobuk.flashcardsapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("colors")
public class ColorsController {
    public ResponseEntity<String> getAllColors() {
        return ResponseEntity.ok("todo");
    }
}
