package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcardsapi.entities.Language;
import com.flashcardsapi.services.LanguagesService;

import java.util.List;

@RestController
@RequestMapping("languages")
@AllArgsConstructor
public class LanguagesController {
    private LanguagesService languagesService;

    @GetMapping()
    public List<Language> getAllLanguages() {
        return languagesService.getAllLanguages();
    }
}
