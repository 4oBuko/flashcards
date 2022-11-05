package com.chornobuk.flashcardsapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chornobuk.flashcardsapi.entities.Tag;
import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.TagsService;
import com.chornobuk.flashcardsapi.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("tags")
@AllArgsConstructor
public class TagsController {

    private TagsService tagsService;

    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Tag>> getUserTags(@PathVariable long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(tagsService.getTagsByUser(user));
    }

    @GetMapping("tag/{tagId}")
    public ResponseEntity<Tag> getTagById(@PathVariable long tagId) {
        return ResponseEntity.ok(tagsService.getTagById(tagId));
    }

    @PostMapping()
    public ResponseEntity<String> addNewTag() {
        return ResponseEntity.ok("todo");
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<String> updateTagById() {
        return ResponseEntity.ok("todo");
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<String> deleteTagById(@PathVariable long tagId) {
        tagsService.deleteTag(tagId);
        return ResponseEntity.ok("todo");
    }
}
