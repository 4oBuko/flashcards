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
    @NotBlank
    private String name;

    @NotNull
    private boolean isPublic;

    @NotNull
    private List<Long> sets;

    @NotNull
    private Long colorId;
}
