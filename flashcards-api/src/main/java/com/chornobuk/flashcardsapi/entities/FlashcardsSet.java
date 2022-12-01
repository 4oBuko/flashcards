package com.chornobuk.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_language_id", referencedColumnName = "id")
    private Language questionLanguage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "answer_language_id", referencedColumnName = "id")
    private Language answerLanguage;

    @Column(length = 500)
    private String description;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "set_id", referencedColumnName = "id")
    private List<Flashcard> flashcards;

    @JsonIgnore
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

//    @JsonManagedReference
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    @JsonGetter(value = "userId")
    public Long getUserId() {
        return user.getId();
    }


    @JsonGetter(value = "questionLanguageId")
    public Long getQuestionLanguageId() {
        return questionLanguage.getId();
    }

    @JsonGetter(value = "answerLanguageId")
    public Long getAnswerLanguageId() {
        return answerLanguage.getId();
    }

    @JsonSetter(value = "questionLanguage")
    public void setJsonQuestionLanguage(Language language) {
        questionLanguage = language;
    }

    @JsonSetter(value = "answerLanguage")
    public void setJsonAnswerLanguage(Language language) {
        answerLanguage = language;
    }

    @JsonSetter(value = "tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonSetter(value = "flashcards")
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    @JsonGetter(value = "tags")
    public Long[] getTags() {
        return tags.stream().map(Tag::getId).toArray(Long[]::new);
    }
}
