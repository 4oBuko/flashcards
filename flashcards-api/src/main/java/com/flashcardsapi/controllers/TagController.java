package com.flashcardsapi.controllers;

import java.util.List;

import com.flashcardsapi.dtos.tag.CreateTagDTO;
import com.flashcardsapi.dtos.tag.UpdateTagDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.db.Tag;
import com.flashcardsapi.services.TagService;
import com.flashcardsapi.services.UserService;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("tags")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    private UserService userService;

    @GetMapping("/{tagId}")
    public Tag getTagById(@PathVariable long tagId,  @AuthenticationPrincipal Jwt jwt) {
        return tagService.getTagById(tagId, jwt);
    }

     @GetMapping()
     public List<Tag> getUserTags(@AuthenticationPrincipal Jwt jwt) {
        return tagService.getUserTags(jwt);
     }

    @PostMapping()
    public Tag addNewTag(@RequestBody @Valid CreateTagDTO dto, @AuthenticationPrincipal Jwt jwt) {
        return tagService.create(dto, jwt);
    }

    @PutMapping()
    public Tag updateTagById(@RequestBody @Valid UpdateTagDTO dto, @AuthenticationPrincipal Jwt jwt) {
        return tagService.update(dto, jwt);
    }

    @DeleteMapping("/{tagId}")
    public String deleteTagById(@PathVariable long tagId,  @AuthenticationPrincipal Jwt jwt) {
        tagService.deleteById(tagId, jwt);
        return "tag was deleted";
    }
}
