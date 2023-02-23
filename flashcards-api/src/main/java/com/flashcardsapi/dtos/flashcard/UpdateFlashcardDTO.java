package com.flashcardsapi.dtos.flashcard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlashcardDTO {
    private Long id;
    private String question;
    private String answer;
}
