package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.Language;

import java.util.ArrayList;
import java.util.List;

public class Languages {
    public static List<Language> getLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language("English"));
        languages.add(new Language("Ukrainian"));
        languages.add(new Language("Japanese"));
//        todo: add more languages
        return languages;
    }
}
