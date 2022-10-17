package com.chornobuk.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    //    can add images as an answer
}
