package com.malgn.domain.content.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateContentRequest {
    private String title;
    private String description;
}
