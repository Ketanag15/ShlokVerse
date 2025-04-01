package com.shlokverse.service;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Optional<Category> getCategoryByName(String name);
}
