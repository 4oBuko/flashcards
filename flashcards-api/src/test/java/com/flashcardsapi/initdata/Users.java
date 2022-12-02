package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AllArgsConstructor
public class Users {

    private final PasswordEncoder encoder;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(
                "test",
                encoder.encode("test"),
                "test",
                LocalDate.of(2022, 10, 31),
                true));

        return users;
    }
}
