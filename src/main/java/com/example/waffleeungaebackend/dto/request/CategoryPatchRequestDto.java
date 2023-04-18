package com.example.waffleeungaebackend.dto.request;

import lombok.Getter;

@Getter
public class CategoryPatchRequestDto {

    private Long categoryId;

    private String categoryName;
}
