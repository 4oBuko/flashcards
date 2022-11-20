package com.chornobuk.flashcardsapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public ResponseEntity<String> addNewTag(@RequestBody Map<String, String> data, @AuthenticationPrincipal Jwt principal) {
        if (!data.containsKey("name") || !data.containsKey("colorId")) {
            return ResponseEntity.badRequest().body("");
        }
        try {
            String name = data.get("name");
            long colorId = Long.parseLong(data.get("colorId"));
            User user = userService.getById((long) principal.getClaims().get("id"));
            tagsService.createNewTag(name, colorId, user);
            return ResponseEntity.ok("Tag was added successfully");

        } catch (ClassCastException | NullPointerException e) {
            return ResponseEntity.badRequest().body("");
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
