package com.malgn.domain.content.service;

import com.malgn.domain.content.dto.CreateContentRequest;
import com.malgn.domain.content.dto.CreateContentResponse;
import com.malgn.domain.content.entity.Content;
import com.malgn.domain.content.repository.ContentRepository;
import com.malgn.global.exception.BusinessException;
import com.malgn.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public CreateContentResponse createContent(CreateContentRequest request) {

        Content content = Content.createContent(request.getTitle(), request.getDescription());

        contentRepository.save(content);

        return CreateContentResponse.from(content);
    }

    @Transactional
    public void deleteContent(Long id, String username, boolean isAdmin) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.CONTENT_NOT_FOUND));

        if(!isAdmin && !username.equals(content.getCreatedBy())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        contentRepository.delete(content);
    }
}
