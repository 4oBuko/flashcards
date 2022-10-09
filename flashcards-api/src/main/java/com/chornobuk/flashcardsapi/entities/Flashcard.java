package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Flashcard {
    private Long id;

    private Long setId;

    private String question;

    private String answer;

//    can add images as an answer
}
