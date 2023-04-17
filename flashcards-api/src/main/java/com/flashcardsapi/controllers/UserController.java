package com.flashcardsapi.controllers;

import com.flashcardsapi.dtos.user.UpdateUserCredentialDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

    // todo: I can take user id for update email, password or nickname from jwt
    @GetMapping("/{userId}")
    public User getUserById(@Valid @PathVariable Long userId) {
        return userService.getById(userId);
    }

    @PutMapping("/password")
    public User updateUserPassword(@RequestBody @Valid UpdateUserCredentialDTO credentialDTO) {
        return userService.updatePassword(credentialDTO);
    }

    @PutMapping("/email")
    public User updateUserEmail(@RequestBody @Valid UpdateUserCredentialDTO credentialDTO) {
        return userService.updateEmail(credentialDTO);
    }

    @PutMapping("/nickname")
    public User updateUserNickname(@RequestBody @Valid UpdateUserCredentialDTO credentialDTO) {
        return userService.updateNickname(credentialDTO);
    }

    @GetMapping("/nickname/{nickname}")
    public Map.Entry<String, Boolean> getNicknameAvailability(@PathVariable String nickname) {
        return Map.entry("isAvailable", userService.isNicknameAvailable(nickname));
    }

    @GetMapping("/{userId}/sets")
    public List<FlashcardsSet> getUserSets(@Valid @PathVariable Long userId) {
        return setsService.getUserSetsById(userId);
    }

    @GetMapping("/{userId}/tags")
    public List<Tag> getUserTags(@Valid @PathVariable long userId) {
        return tagsService.getUserTagsById(userId);
    }
}
