package com.flashcardsapi.dtos.flashcardsset;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    @NotNull(message = "answer language cannot be null")
    private Long answerLanguageId;

    @NotNull(message = "question language cannot be null")
    private Long questionLanguageId;

    private String description;

    @NotEmpty(message = "set must contain at least one flashcard")
    private List<Flashcard> flashcards;

    @JsonAlias("isPublic")
    private boolean isPublic;

}
