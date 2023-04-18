package com.example.waffleeungaebackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
public class PostPatchRequestDto {
    @NotEmpty(message="내용은 필수 항목입니다.")
    private String content;

    private Long categoryId;
}
