package com.flashcardsapi.dtos.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCredentialDTO {
    @NotBlank(message = "credential cannot be null")
    @JsonAlias({"newNickname", "newEmail", "newPassword"})
    private String credential;

//    @JsonSetter("newNickname")
//    public void setNickname(String newNickname) {
//        this.credential = newNickname;
//    }
//
//    @JsonSetter("newEmail")
//    public void setEmail(String newEmail) {
//        this.credential = newEmail;
//    }
//
//    @JsonSetter("newPassword")
//    public void setPassword(String newPassword) {
//        this.credential = newPassword;
//    }

}
