package com.shlokverse.implementation;

import com.shlokverse.model.Category;
import com.shlokverse.repository.CategoryRepository;
import com.shlokverse.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    // This method can be used to create new categories if they don't exist.
    public Category createCategory(String categoryName) {
        Category category = new Category(categoryName);
        return categoryRepository.save(category);
    }

    // Finds an existing category or creates one if not found.
    public Category findOrCreateCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseGet(() -> categoryRepository.save(new Category(categoryName)));
    }
}
