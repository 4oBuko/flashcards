package com.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne(fetch = FetchType.LAZY)
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

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "flashcards_set_tag", joinColumns = @JoinColumn(name = "flashcards_set_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    private boolean isPublic;

    @JsonGetter(value = "userId")
    public Long getUserId() {
        return user.getId();
    }

    @JsonSetter(value = "userId")
    public void setUserId(Long userId) {
        User user = new User();
        user.setId(id);
        this.setUser(user);
    }

    @JsonSetter(value = "questionLanguageId")
    public void setQuestionLanguageId(Long questionLanguageId) {
        Language language = new Language();
        language.setId(questionLanguageId);
        this.setQuestionLanguage(language);
    }

    @JsonSetter(value = "answerLanguageId")
    public void setAnswerLanguageId(Long answerLanguageId) {
        Language language = new Language();
        language.setId(answerLanguageId);
        this.setQuestionLanguage(language);
    }
}
