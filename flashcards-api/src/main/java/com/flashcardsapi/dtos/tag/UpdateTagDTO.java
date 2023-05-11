package com.flashcardsapi.dtos.tag;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTagDTO {

    @NotNull(message = "tag must have name")
    private Long id;

    @NotBlank(message = "tag must have name")
    private String name;

    @JsonAlias("isPublic")
    private boolean isPublic;

    @NotNull(message = "tag must have color")
    private Long colorId;

    @NotNull
    private List<Long> sets;

}
