package com.flashcardsapi.entities.db;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Flashcard implements Serializable {

    private int index;

    @Column(length = 500)
    private String question;
    @Column(length = 500)
    private String answer;

}
