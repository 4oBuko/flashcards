package com.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // delete set I need only id
    // in getter you can left set id too because it's will be easier to
    // work with on the front end (At least I think so)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "set_id", referencedColumnName = "id")
    private FlashcardsSet set;

    @Column(length = 500)
    private String question;
    @Column(length = 500)
    private String answer;

    public Flashcard(FlashcardsSet set, String question, String answer) {
        this.set = set;
        this.question = question;
        this.answer = answer;
    }

    @JsonBackReference
    public FlashcardsSet getSet() {
        return set;
    }
}
