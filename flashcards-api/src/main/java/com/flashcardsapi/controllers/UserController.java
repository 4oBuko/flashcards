package com.flashcardsapi.controllers;

import com.flashcardsapi.dtos.user.UpdateUserCredentialDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.db.FlashcardsSet;
import com.flashcardsapi.entities.db.Tag;
import com.flashcardsapi.entities.db.User;
import com.flashcardsapi.services.FlashcardsSetService;
import com.flashcardsapi.services.TagsService;
import com.flashcardsapi.services.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final FlashcardsSetService setsService;
    private final TagsService tagsService;

    @GetMapping("/id/{userId}")
    public User getUserById(@Valid @PathVariable Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("/{nickname}")
    public User getByNickname(@PathVariable String nickname) {
        return userService.getByNickname(nickname);
    }

    @PutMapping("/password")
    public User updateUserPassword(@RequestBody @Valid UpdateUserCredentialDTO credentialDTO, @AuthenticationPrincipal Jwt jwt) {
        return userService.updatePassword(credentialDTO, jwt);
    }

    @PutMapping("/email")
    public User updateUserEmail(@RequestBody @Valid UpdateUserCredentialDTO credentialDTO, @AuthenticationPrincipal Jwt jwt) {
        return userService.updateEmail(credentialDTO,jwt);
    }

    @PutMapping("/nickname")
    public User updateUserNickname(@RequestBody @Valid UpdateUserCredentialDTO credentialDTO, @AuthenticationPrincipal Jwt jwt) {
        return userService.updateNickname(credentialDTO, jwt);
    }

    @GetMapping("/nickname/{nickname}")
    public Map.Entry<String, Boolean> getNicknameAvailability(@PathVariable String nickname) {
        return Map.entry("isAvailable", userService.isNicknameAvailable(nickname));
    }

    @GetMapping("/{userId}/sets")
    public List<FlashcardsSet> getUserSets(@Valid @PathVariable Long userId, @AuthenticationPrincipal Jwt jwt) {
        return setsService.getUserSetsById(userId, jwt);
    }

    @GetMapping("/{userId}/tags")
    public List<Tag> getUserTags(@Valid @PathVariable long userId, @AuthenticationPrincipal Jwt jwt) {
        return tagsService.getUserTagsById(userId);
    }
}
