package com.flashcardsapi.dtos.flashcardsset;

import com.flashcardsapi.entities.db.Flashcard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateFlashcardsSetDTO {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull
    private Long answerLanguageId;

    @NotNull
    private Long questionLanguageId;

    private String Description;

    @NotEmpty(message = "set must contain at least one flashcard")
    private List<Flashcard> flashcards;

    private boolean isPublic;
}
