package com.shlokverse.implementation;

import com.shlokverse.model.Category;
import com.shlokverse.repository.CategoryRepository;
import com.shlokverse.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    //find all categories without any filters
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //find categories filtering on ID
    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    //find categories, filtering on category name
    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }
}