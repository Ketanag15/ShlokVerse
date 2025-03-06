package com.shlokverse.service;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getCategoriesByGod(God god);
    Optional<Category> getCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategory(Long id);
}
