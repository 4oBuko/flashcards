package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.Flashcard;

public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
    void deleteAllBySetId(Long setId);
}
