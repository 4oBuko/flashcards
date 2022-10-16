package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.FlashcardsSet;
import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.repositories.FlashcardsSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlashcardsSetsService {

    private FlashcardsSetRepository flashcardsSetRepository;

    public List<FlashcardsSet> getSetsByUser(User user) {
        return flashcardsSetRepository.getFlashcardsSetByUser(user);
    }
}
