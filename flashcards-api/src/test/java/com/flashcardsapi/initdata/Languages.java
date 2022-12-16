package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.Language;

import java.util.ArrayList;
import java.util.List;

public class Languages {

    private static List<Language> languages = new ArrayList<>();
    static {
        languages.add(new Language("English"));
        languages.add(new Language("Ukrainian"));
        languages.add(new Language("Japanese"));
        // todo: add more languages
    }

    public static Language getLanguageById(int id) {
        return languages.get(id - 1);
    }

    public static List<Language> getLanguages() {
        return languages;
    }
}
