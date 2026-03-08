package com.malgn.domain.content.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateContentRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String description;
}
