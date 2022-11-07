package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.Flashcard;
import org.springframework.data.repository.CrudRepository;

public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
    void deleteAllBySetId(Long setId);
}
