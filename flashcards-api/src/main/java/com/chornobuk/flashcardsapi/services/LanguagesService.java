package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.Language;
import com.chornobuk.flashcardsapi.repositories.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
