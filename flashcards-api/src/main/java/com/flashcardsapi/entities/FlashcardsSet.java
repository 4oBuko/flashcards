package com.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // delete user I need only id
     @JsonIgnore
     @ManyToOne
     @JoinColumn(name = "user_id", referencedColumnName = "id")
     private User user;

//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private Long userId;

    @Column(length = 50)
    private String name;

    // only id
    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name = "question_language_id", referencedColumnName = "id")
    // private Language questionLanguage;

    @JoinColumn(name = "question_language_id", referencedColumnName = "id")
    private Long questionLanguageId;

    // only id
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "answer_language_id", referencedColumnName = "id")
//    private Language answerLanguage;

    @Column(name = "answer_language_id", insertable = false, updatable = false)
    private Long answerLanguageId;

    @Column(length = 500)
    private String description;

    // @JsonIgnore
    @OneToMany
    @JoinColumn(name = "set_id", referencedColumnName = "id")
    private List<Flashcard> flashcards;

    @JsonIgnore
    @ManyToMany(mappedBy = "sets", fetch = FetchType.LAZY)
    private List<Tag> tags;

    private boolean isPublic;

    public FlashcardsSet(User user, String name, Long questionLanguageId, Long answerLanguageId, String description,
                         List<Flashcard> flashcards, List<Tag> tags, boolean isPublic) {
        this.user = user;
        this.name = name;
        this.questionLanguageId = questionLanguageId;
        this.answerLanguageId = answerLanguageId;
        this.description = description;
        this.flashcards = flashcards;
        this.tags = tags;
        this.isPublic = isPublic;
    }
}
