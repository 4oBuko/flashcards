package com.flashcardsapi.dtos;

import com.flashcardsapi.annotations.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "email cannot be null")
    @Email
    private String email;

    @NotBlank(message = "password cannot be null")
//    @ValidPassword
    private String password;

    private boolean stayLoggedIn;
}
