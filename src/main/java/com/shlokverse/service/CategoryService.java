package com.shlokverse.service;

import com.shlokverse.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long categoryId);
    Optional<Category> getCategoryByName(String categoryName);
}