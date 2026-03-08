package com.malgn.domain.content.controller;

import com.malgn.domain.content.dto.CreateContentRequest;
import com.malgn.domain.content.dto.CreateContentResponse;
import com.malgn.domain.content.dto.GetContentResponse;
import com.malgn.domain.content.service.ContentService;
import com.malgn.global.dto.ApiResponse;
import com.malgn.global.security.service.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateContentResponse>> createContent(@Valid @RequestBody CreateContentRequest request) {

        CreateContentResponse data = contentService.createContent(request);

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠를 추가했습니다.", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetContentResponse>> getContent(@PathVariable("id") Long id) {

        GetContentResponse data = contentService.getContentById(id);

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠 상세 조회 내용입니다. 콘텐츠 ID: " + data.getId(), data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContent(@PathVariable Long id,
                                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {

        contentService.deleteContent(id, userPrincipal.getUsername(), userPrincipal.isAdmin());

        return ResponseEntity.ok().body(ApiResponse.success("콘텐츠를 삭제했습니다.", null));
    }
}
