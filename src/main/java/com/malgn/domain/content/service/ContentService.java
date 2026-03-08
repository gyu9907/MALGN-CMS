package com.malgn.domain.content.service;

import com.malgn.domain.content.dto.*;
import com.malgn.domain.content.entity.Content;
import com.malgn.domain.content.repository.ContentRepository;
import com.malgn.global.exception.BusinessException;
import com.malgn.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public ContentDetailResponse getContentById(Long id) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.CONTENT_NOT_FOUND));

        contentRepository.increaseViewCount(id); // 조회수 증가 (DTO에는 반영 안 됨)

        return ContentDetailResponse.from(content);
    }

    public Page<ContentSummaryResponse> getContents(Pageable pageable) {

        Page<Content> content = contentRepository.findAll(pageable);

        return content.map(ContentSummaryResponse::from);
    }

    @Transactional
    public CreateContentResponse createContent(CreateContentRequest request) {

        Content content = Content.createContent(request.getTitle(), request.getDescription());

        contentRepository.save(content);

        return CreateContentResponse.from(content);
    }

    @Transactional
    public ContentDetailResponse updateContent(UpdateContentRequest request, Long id, String username, boolean isAdmin) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.CONTENT_NOT_FOUND));

        // 관리자 또는 작성자 본인인지 확인
        if(!isAdmin && !username.equals(content.getCreatedBy())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        content.updateContent(request.getTitle(), request.getDescription()); // 콘텐츠 수정

        contentRepository.flush(); // 수정자와 수정일이 반영되도록 flush() 호출

        return ContentDetailResponse.from(content);
    }

    @Transactional
    public void deleteContent(Long id, String username, boolean isAdmin) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.CONTENT_NOT_FOUND));

        // 관리자 또는 작성자 본인인지 확인
        if(!isAdmin && !username.equals(content.getCreatedBy())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        contentRepository.delete(content);
    }
}
