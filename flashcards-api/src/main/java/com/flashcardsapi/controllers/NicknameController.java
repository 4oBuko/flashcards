package com.flashcardsapi.controllers;

import com.flashcardsapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/nicknames")
public class NicknameController {

    private UserService userService;

    @GetMapping("/{nickname}")
    public Map.Entry<String, Boolean> getNicknameAvailability(@PathVariable String nickname) {
        return Map.entry("isAvailable", userService.isNicknameAvailable(nickname));
    }
}
