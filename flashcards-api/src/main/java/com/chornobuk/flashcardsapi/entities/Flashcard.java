package com.chornobuk.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
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


//    @JsonSetter(value = "setId")
//    public void setSetId(Long setId) {
//
//    }
    //    can add images as an answer
}
