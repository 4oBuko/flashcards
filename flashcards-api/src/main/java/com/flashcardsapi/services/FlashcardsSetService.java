package com.flashcardsapi.services;

import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcardsapi.entities.*;
import com.flashcardsapi.repositories.FlashcardRepository;
import com.flashcardsapi.repositories.FlashcardsSetRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FlashcardsSetService {

    private FlashcardsSetRepository flashcardsSetRepository;
    private FlashcardRepository flashcardRepository;

    @Transactional
    public FlashcardsSet updateSet(FlashcardsSet setToUpdate) throws IllegalArgumentException {
        flashcardRepository.saveAll(setToUpdate.getFlashcards());
        return flashcardsSetRepository.save(setToUpdate);
    }

    @Transactional
    public FlashcardsSet addNewSet(FlashcardsSet newSet, User user) {
        newSet.setUser(user);
        newSet.setFlashcards((List<Flashcard>) flashcardRepository.saveAll(newSet.getFlashcards()));
        return flashcardsSetRepository.save(newSet);
    }

    public FlashcardsSet getSetById(long setId) {
        return flashcardsSetRepository.findById(setId).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Transactional
    public void deleteSetById(long setId) {
        flashcardRepository.deleteAllBySetId(setId);
        flashcardsSetRepository.deleteById(setId);
    }

    @Transactional
    public List<FlashcardsSet> getUserSetsById(Long userId) {
        return flashcardsSetRepository.findAllByUser_id(userId);
    }
}
