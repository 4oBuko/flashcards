package com.chornobuk.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(length = 50)
    private String name;
    @ManyToOne
    @JoinColumn(name = "question_language_id", referencedColumnName = "id")
    private Language questionLanguage;

    @ManyToOne
    @JoinColumn(name = "answer_language_id", referencedColumnName = "id")
    private Language answerLanguage;

    @Column(length = 500)
    private String description;

    @OneToMany
    @JoinColumn(name = "set_id", referencedColumnName = "id")
    private List<Flashcard> flashcards;

    @ManyToMany(mappedBy = "sets")
    private List<Tag> tags;

    private boolean isPublic;

    public FlashcardsSet(User user, String name, Language questionLanguage, Language answerLanguage, String description, List<Flashcard> flashcards, List<Tag> tags, boolean isPublic) {
        this.user = user;
        this.name = name;
        this.questionLanguage = questionLanguage;
        this.answerLanguage = answerLanguage;
        this.description = description;
        this.flashcards = flashcards;
        this.tags = tags;
        this.isPublic = isPublic;
    }

    @JsonManagedReference
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }
}
