package com.flashcardsapi.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.Tag;
import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.TagsService;
import com.flashcardsapi.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("tags")
@AllArgsConstructor
public class TagsController {

    private TagsService tagsService;

    private UserService userService;

    @GetMapping("/{tagId}")
    public ResponseEntity<Tag> getTagById(@PathVariable long tagId) {
        return ResponseEntity.ok(tagsService.getTagById(tagId));
    }

    @PostMapping()
    public ResponseEntity<Tag> addNewTag(@RequestBody Map<String, String> data, @AuthenticationPrincipal Jwt principal) {
        if (!data.containsKey("name") || !data.containsKey("colorId")) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            String name = data.get("name");
            long colorId = Long.parseLong(data.get("colorId"));
            User user = userService.getById((long) principal.getClaims().get("id"));
            Tag savedTag = tagsService.createNewTag(name, colorId, user);
            return ResponseEntity.ok(savedTag);

        } catch (ClassCastException | NullPointerException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping()
    public ResponseEntity<Tag> updateTagById(@RequestBody Map<String, String> body) {
        try {
            String newName = body.get("name");
            Long colorId = Long.valueOf(body.get("colorId"));
            Long tagId = Long.valueOf(body.get("id"));
            Tag updatedTag = tagsService.updateTag(tagId, colorId, newName);
            return ResponseEntity.ok(updatedTag);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<String> deleteTagById(@PathVariable long tagId) {
        tagsService.deleteTag(tagId);
        return ResponseEntity.ok("tag was deleted");
    }
}
