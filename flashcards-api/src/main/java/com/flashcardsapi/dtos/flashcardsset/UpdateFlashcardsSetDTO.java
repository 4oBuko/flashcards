package com.flashcardsapi.dtos.flashcardsset;

import com.flashcardsapi.dtos.flashcard.CreateFlashcardDTO;
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
public class UpdateFlashcardsSetDTO {

    @NotNull
    private Long id;

    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotNull
    private Long answerLanguageId;
    @NotNull
    private Long questionLanguageId;

    @NotNull
    private List<Long> setTags;

    private String Description;

    @NotEmpty(message = "set must contain at least one flashcard")
    private List<CreateFlashcardDTO> flashcards;

    //    private boolean isPublic;
}
