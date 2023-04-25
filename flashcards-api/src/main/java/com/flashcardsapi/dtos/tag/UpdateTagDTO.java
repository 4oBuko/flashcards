package com.flashcardsapi.dtos.tag;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTagDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private boolean isPublic;

    @NotNull
    private Long colorId;

    @NotNull
    private List<Long> sets;

}
