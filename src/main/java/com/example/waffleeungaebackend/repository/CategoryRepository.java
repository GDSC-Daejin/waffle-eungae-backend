package com.example.waffleeungaebackend.repository;

import com.example.waffleeungaebackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
