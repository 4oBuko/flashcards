package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private LocalDate registrationDate;

}
