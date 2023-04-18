package com.example.waffleeungaebackend.service;

import com.example.waffleeungaebackend.dto.CategoryDto;
import com.example.waffleeungaebackend.dto.request.CategoryCreateRequestDto;
import com.example.waffleeungaebackend.dto.request.CategoryPatchRequestDto;
import com.example.waffleeungaebackend.entity.Category;
import com.example.waffleeungaebackend.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(new Category());
    }

    @Override
    public Category addCategory(CategoryCreateRequestDto categoryCreateRequestDto) {
        return categoryRepository.save(categoryCreateRequestDto.toEntity());
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateById(Long id, CategoryPatchRequestDto categoryPatchRequestDto){
        Category category = this.findById(id);
        category.setCategoryName(categoryPatchRequestDto.getCategoryName());
        return this.categoryRepository.save(category);
    }


    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> categorysList = categoryRepository.findAll();
        return categorysList.stream().map(Category::toDto).collect(Collectors.toList());
    }
}
