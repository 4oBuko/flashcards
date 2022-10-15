package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @Column(length = 50)
    private String nickname;

    private LocalDate registrationDate;

    public User(String email, String password, String nickname, LocalDate registrationDate) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.registrationDate = registrationDate;
    }
}
