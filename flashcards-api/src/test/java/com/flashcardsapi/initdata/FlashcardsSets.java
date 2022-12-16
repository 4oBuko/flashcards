package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.FlashcardsSet;
import com.flashcardsapi.entities.Language;
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
        Language l1 = Languages.getLanguageById(1);
        Language l2 = Languages.getLanguageById(2);
        // todo: add flashcards and tags
        sets.add(new FlashcardsSet(null, user, "numbers", l2, l1, "English numbers", null, null, false));
        sets.add(new FlashcardsSet(null, user, "Cubes", l1, l2, "algebra", null, null, true));
        sets.add(new FlashcardsSet(null, user, "Solar system", l1, l1, "solar system planets", null, null, false));
        sets.add(new FlashcardsSet(null, user, "Countries", l1, l1, "top 2 countries by size", null, null, true));
        return sets;
    }
}
