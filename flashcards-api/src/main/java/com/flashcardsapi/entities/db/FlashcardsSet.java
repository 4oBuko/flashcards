package com.flashcardsapi.entities.db;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class FlashcardsSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "question_language_id", referencedColumnName = "id", nullable = false)
    private Language questionLanguage;

    @ManyToOne
    @JoinColumn(name = "answer_language_id", referencedColumnName = "id", nullable = false)
    private Language answerLanguage;

    @Column(length = 500)
    private String description;


    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Flashcard> flashcards;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "flashcards_set_tag",
            joinColumns = @JoinColumn(name = "flashcards_set_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    @JsonIgnoreProperties(value = "flashcards")
    private List<Tag> tags = new ArrayList<>();

    private boolean isPublic;

    @JsonGetter(value = "userId")
    public Long getUserId() {
        return user.getId();
    }
}
