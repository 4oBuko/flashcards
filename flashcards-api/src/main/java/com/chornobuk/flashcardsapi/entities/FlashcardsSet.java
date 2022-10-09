package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlashcardsSet {
    private Long id;

    private String name;
    private Language questionLanguage;
    private Language answerLanguage;

    private Tag[] tags;
//    add answer and question language
}
