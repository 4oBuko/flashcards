package com.chornobuk.flashcardsapi.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

//todo: format entity json format by jackson
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "flashcards_set_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flashcards_set_id", referencedColumnName = "id")
    )
    private List<FlashcardsSet> sets;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;


    public Tag(User user, String name, Color color) {
        this.user = user;
        this.name = name;
        this.color = color;
    }

    @JsonGetter(value = "userId")
    public Long jsonUserId() {
        return user.getId();
    }

    @JsonGetter(value = "colorId")
    public Long jsonColorId() {
        return color.getId();
    }
}
