package com.flashcardsapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String password;
    private boolean stayLoggedIn;
}
