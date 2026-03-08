package com.malgn.domain.content.dto;

import com.malgn.domain.content.entity.Content;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetContentResponse {
    private Long id;
    private String title;
    private String description;
    private Long viewCount;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;

    public static GetContentResponse from(Content content) {
        return GetContentResponse.builder()
                .id(content.getId())
                .title(content.getTitle())
                .description(content.getDescription())
                .viewCount(content.getViewCount())
                .createdDate(content.getCreatedDate())
                .createdBy(content.getCreatedBy())
                .lastModifiedDate(content.getLastModifiedDate())
                .lastModifiedBy(content.getLastModifiedBy())
                .build();
    }
}
