package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.Flashcard;

public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
    void deleteAllBySetId(Long setId);
}
