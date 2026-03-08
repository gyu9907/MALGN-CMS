package com.malgn.domain.content.controller;

import com.malgn.domain.content.dto.*;
import com.malgn.domain.content.service.ContentService;
import com.malgn.global.dto.ApiResponse;
import com.malgn.global.dto.PageResponse;
import com.malgn.global.security.service.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContentDetailResponse>> getContent(@PathVariable("id") Long id) {

        ContentDetailResponse data = contentService.getContentById(id);

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠 상세 조회 내용입니다. 콘텐츠 ID: " + data.getId(), data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ContentSummaryResponse>>> getContents(
            @Valid GetContentsRequest request) {

        Page<ContentSummaryResponse> data = contentService.getContents(request.toPageable());

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠 목록 조회 내용입니다.", PageResponse.from(data)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateContentResponse>> createContent(@Valid @RequestBody CreateContentRequest request) {

        CreateContentResponse data = contentService.createContent(request);

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠를 추가했습니다.", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContent(@PathVariable Long id,
                                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {

        contentService.deleteContent(id, userPrincipal.getUsername(), userPrincipal.isAdmin());

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠를 삭제했습니다.", null));
    }
}
