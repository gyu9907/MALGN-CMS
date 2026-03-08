package com.malgn.domain.content.dto;

import com.malgn.domain.content.entity.Content;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContentSummaryResponse {
    private Long id;
    private String title;
    private Long viewCount;
    private LocalDateTime createdDate;
    private String createdBy;

    public static ContentSummaryResponse from(Content content) {
        return ContentSummaryResponse.builder()
                .id(content.getId())
                .title(content.getTitle())
                .viewCount(content.getViewCount())
                .createdDate(content.getCreatedDate())
                .createdBy(content.getCreatedBy())
                .build();
    }
}