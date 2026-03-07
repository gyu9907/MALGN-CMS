package com.malgn.domain.content.controller;

import com.malgn.domain.content.dto.CreateContentRequest;
import com.malgn.domain.content.dto.CreateContentResponse;
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
}
