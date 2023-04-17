package com.flashcardsapi.entities.db;

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

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "set_id", nullable = false)
    private FlashcardsSet set;

    @Column(length = 500)
    private String question;
    @Column(length = 500)
    private String answer;

}
