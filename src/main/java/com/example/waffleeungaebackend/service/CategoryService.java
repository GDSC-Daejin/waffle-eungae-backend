package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.CategoryDto;
import com.example.waffleeungaebackend.dto.request.CategoryCreateRequestDto;
import com.example.waffleeungaebackend.dto.request.CategoryPatchRequestDto;
import com.example.waffleeungaebackend.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findById(Long id);

    Category addCategory(CategoryCreateRequestDto categoryCreateRequestDto);

    Category updateById(Long id, CategoryPatchRequestDto categoryPatchRequestDto);

    void deleteCategory(Long id);

    List<CategoryDto> findAllCategory();
}
