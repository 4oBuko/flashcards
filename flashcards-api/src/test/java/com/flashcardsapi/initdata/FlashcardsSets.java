package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.FlashcardsSet;
import com.flashcardsapi.entities.User;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class FlashcardsSets {
    private Users users;
    public List<FlashcardsSet> getSets() {
        List<FlashcardsSet> sets = new ArrayList<>();
        User user = users.getUsers().get(0);
//        todo: add flashcards and tags
        sets.add(new FlashcardsSet(user, "numbers", 2L, 1L, "English numbers", null, null, false));
        sets.add(new FlashcardsSet(user, "Cubes", 1L, 1L, "algebra", null, null, true));
        sets.add(new FlashcardsSet(user, "Solar system", 1L, 1L, "solar system planets", null, null, false));
        sets.add(new FlashcardsSet(user, "Countries",1L, 1L, "top 2 countries by size", null, null, true));
        return sets;
    }
}
