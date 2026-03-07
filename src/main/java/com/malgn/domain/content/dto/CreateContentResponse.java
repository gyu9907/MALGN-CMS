package com.malgn.domain.content.dto;

import com.malgn.domain.content.entity.Content;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreateContentResponse {
    private Long id;
    private LocalDateTime createDate;

    public static CreateContentResponse from(Content content) {
        return CreateContentResponse.builder()
                .id(content.getId())
                .createDate(content.getCreatedDate())
                .build();
    }
}
