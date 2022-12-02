package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.FlashcardsSet;

import java.util.ArrayList;
import java.util.List;

public class FlashcardsSets {
    public static List<FlashcardsSet> getSets() {
        List<FlashcardsSet> sets = new ArrayList<>();
//        todo: add flashcards and tags
        sets.add(new FlashcardsSet(1L, "numbers", 2L, 1L, "English numbers", null, null, false));
        sets.add(new FlashcardsSet(1L, "Cubes", 1L, 1L, "algebra", null, null, true));
        sets.add(new FlashcardsSet(1L, "Solar system", 1L, 1L, "solar system planets", null, null, false));
        sets.add(new FlashcardsSet(1L, "Countries",1L, 1L, "top 2 countries by size", null, null, true));
        return sets;
    }
}
