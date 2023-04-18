package com.example.waffleeungaebackend.dto.request;

import com.example.waffleeungaebackend.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
public class CategoryCreateRequestDto {

    private Long categoryId;

    @Schema(description = "카테고리 이름.", example = "카테고리 이름")
    @NotEmpty(message = "카테고리는 필수 항목입니다.")
    private String categoryName;

    public Category toEntity(){
        return Category.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }
}
