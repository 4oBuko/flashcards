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

    public FlashcardsSet updateSet(FlashcardsSet updatedSet) {
        return flashcardsSetRepository.save(updatedSet);
    }

    @Transactional
    public FlashcardsSet addNewSet(FlashcardsSet newSet, User user) {
        newSet.setUser(user);
        newSet.setFlashcards((List<Flashcard>) flashcardRepository.saveAll(newSet.getFlashcards()));
        FlashcardsSet savedSet = flashcardsSetRepository.save(newSet);
//      use a variable for saved set because of transaction
        return savedSet;
    }
}