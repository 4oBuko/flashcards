package com.chornobuk.flashcardsapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tags")
public class TagsController {
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserTags() {
        return ResponseEntity.ok("todo") ;
    }

    @GetMapping("tag/{tagId}")
    public ResponseEntity<String> getTagById() {
        return ResponseEntity.ok("todo") ;
    }

    @PostMapping()
    public ResponseEntity<String> addNewTag(){
        return ResponseEntity.ok("todo") ;
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<String> updateTagById() {
        return ResponseEntity.ok("todo") ;
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<String> deleteTagById() {
        return ResponseEntity.ok("todo") ;
    }
}
