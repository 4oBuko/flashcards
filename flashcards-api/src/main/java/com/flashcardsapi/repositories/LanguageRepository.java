package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
}
