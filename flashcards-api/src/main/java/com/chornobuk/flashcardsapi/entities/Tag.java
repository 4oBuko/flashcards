package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Tag {
    private Long id;

    private String name;

    private FlashcardsSet[] sets;
//    can add color
}
