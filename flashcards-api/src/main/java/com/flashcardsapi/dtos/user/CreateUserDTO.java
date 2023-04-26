package com.flashcardsapi.dtos.user;

import com.flashcardsapi.annotations.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateUserDTO {
    @Column(unique = true)
    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "nickname cannot be empty")
    private String nickname;

    @ValidPassword
    @NotBlank(message = "password cannot be empty")
    private String password;
}
