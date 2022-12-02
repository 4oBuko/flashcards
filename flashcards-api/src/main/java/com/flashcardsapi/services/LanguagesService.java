package com.flashcardsapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.Language;
import com.flashcardsapi.repositories.LanguageRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class LanguagesService {

    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return StreamSupport.stream(languageRepository.findAll().spliterator(), false)
                .toList();
    }
}
