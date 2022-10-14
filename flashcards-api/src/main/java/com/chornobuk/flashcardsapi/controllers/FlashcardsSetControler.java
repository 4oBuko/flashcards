package com.chornobuk.flashcardsapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sets")
public class FlashcardsSetControler {
    @GetMapping("/set/{Setid}")
    public ResponseEntity<String> getSetById() {
        return ResponseEntity.ok("todo") ;
    }

    @GetMapping("{userId}")
    public ResponseEntity<String> getUserTags() {
        return ResponseEntity.ok("todo") ;
    }

    @PostMapping()
    public ResponseEntity<String> addNewTag() {
        return ResponseEntity.ok("todo") ;
    }

    @PutMapping("/{setId}")
    public ResponseEntity<String> updateSetById() {
        return ResponseEntity.ok("todo") ;
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity<String> deleteTagById() {
        return ResponseEntity.ok("todo") ;
    }


}
