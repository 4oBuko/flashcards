package com.flashcardsapi.entities;

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

    @Column(name="set_id")
    private Long setId;

    @Column(length = 500)
    private String question;
    @Column(length = 500)
    private String answer;

    public Flashcard(String question, String answer, Long setId) {
        this.setId = setId;
        this.question = question;
        this.answer = answer;
    }
}
