package com.malgn.domain.content.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class GetContentsRequest {

    @Min(1)
    @Max(10000) // 너무 큰 페이지 번호 방지
    private int page = 1;

    @Min(1)
    @Max(50) // 한 번에 너무 많은 데이터 요청 방지
    private int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page - 1, size,
                Sort.by(Sort.Direction.DESC, "createdDate").and(Sort.by(Sort.Direction.DESC, "id")));
    }
}
