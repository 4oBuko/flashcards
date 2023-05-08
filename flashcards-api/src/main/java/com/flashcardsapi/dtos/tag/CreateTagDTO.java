package com.flashcardsapi.dtos.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagDTO {

    @NotBlank(message = "tag must have name")
    private String name;

    private boolean isPublic;

    @NotNull(message = "tag must have sets")
    private List<Long> sets;

    @NotNull(message = "tag must have color")
    private Long colorId;
}
