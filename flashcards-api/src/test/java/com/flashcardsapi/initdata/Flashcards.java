package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class Flashcards {
    public static List<Flashcard> getFlashcards() {
        List<Flashcard> list = new ArrayList<>();
        list.add(new Flashcard("Mercury", "1st planet", 3L));
        list.add(new Flashcard("Venus", "2nd planet", 3L));
        list.add(new Flashcard( "Earth", "3rd planet", 3L));
        list.add(new Flashcard("Mars", "4th planet", 3L));
        list.add(new Flashcard("Jupiter", "5th planet", 3L));
        list.add(new Flashcard("Saturn", "6th planet", 3L));
        list.add(new Flashcard("Uranus", "7th planet", 3L));
        list.add(new Flashcard("Neptune", "8th planet", 3L));
        list.add(new Flashcard("one", "1", 1L));
        list.add(new Flashcard("two", "2", 1L));
        list.add(new Flashcard( "three", "3", 1L));
        list.add(new Flashcard("four", "4", 1L));
        list.add(new Flashcard("five", "5", 1L));
        list.add(new Flashcard("27", "3^3", 2L));
        list.add(new Flashcard("64", "4^3", 2L));
        list.add(new Flashcard( "125", "5^3", 2L));
        list.add(new Flashcard("russia", "1st", 4L));
        list.add(new Flashcard("Canada", "2nd", 4L));
        return list;
    }
}
