package com.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "nickname cannot be empty")
    @Column(length = 50)
    private String nickname;

    @JsonIgnore
    private LocalDate registrationDate;

    @JsonIgnore
    private boolean isConfirmed;

    public User(String email, String password, String nickname, LocalDate registrationDate) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.registrationDate = registrationDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String email, String password, String nickname, LocalDate registrationDate, boolean isConfirmed) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.registrationDate = registrationDate;
        this.isConfirmed = isConfirmed;
    }
}
