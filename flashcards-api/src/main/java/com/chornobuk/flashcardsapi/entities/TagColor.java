package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TagColor {
    @Id
    private Long id;

    private String colorCode;
}
