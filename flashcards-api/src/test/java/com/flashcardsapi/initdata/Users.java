package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Users {

    private final PasswordEncoder encoder;

    private List<User> users = new ArrayList<>();

    public Users(PasswordEncoder encoder) {
        this.encoder = encoder;
        users.add(new User(
                "test",
                this.encoder.encode("test"),
                "test",
                LocalDate.of(2022, 10, 31),
                true));
    }

    public List<User> getUsers() {
        return users;
    }
}
