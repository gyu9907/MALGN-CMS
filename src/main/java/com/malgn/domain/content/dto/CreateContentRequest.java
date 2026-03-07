package com.malgn.domain.content.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateContentRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
