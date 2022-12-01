package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.*;
import com.chornobuk.flashcardsapi.repositories.FlashcardRepository;
import com.chornobuk.flashcardsapi.repositories.FlashcardsSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FlashcardsSetsService {

    private FlashcardsSetRepository flashcardsSetRepository;
    private FlashcardRepository flashcardRepository;

    
    public List<FlashcardsSet> getSetsByUser(User user) {
        //        todo: check who made the request
        return flashcardsSetRepository.getFlashcardsSetByUser(user);
    }

    public void deleteSet(FlashcardsSet set) {
        flashcardsSetRepository.delete(set);
    }

    @Transactional
    public FlashcardsSet updateSet(FlashcardsSet setToUpdate) throws IllegalArgumentException {
        flashcardRepository.deleteAllBySetId(setToUpdate.getId());
        flashcardRepository.saveAll(setToUpdate.getFlashcards());
        return flashcardsSetRepository.save(setToUpdate);
    }

    @Transactional
    public void addNewSet(FlashcardsSet newSet, User user) {
        newSet.setUser(user);
        newSet.setFlashcards((List<Flashcard>) flashcardRepository.saveAll(newSet.getFlashcards()));
        flashcardsSetRepository.save(newSet);
    }

    public FlashcardsSet getSetById(long setId) {
        return flashcardsSetRepository.findById(setId).orElse(null);
    }

    @Transactional
    public void deleteSetById(long setId) {
        flashcardRepository.deleteAllBySetId(setId);
        flashcardsSetRepository.deleteById(setId);
    }
}
