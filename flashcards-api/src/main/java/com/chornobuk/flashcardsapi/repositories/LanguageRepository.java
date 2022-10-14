package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, Long> {
}
