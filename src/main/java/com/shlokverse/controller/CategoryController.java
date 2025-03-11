package com.shlokverse.controller;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.service.CategoryService;
import com.shlokverse.service.GodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;
    private GodService godService;

    @Autowired
    public CategoryController(CategoryService categoryService, GodService godService ){
        this.categoryService = categoryService;
        this.godService = godService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/god/{godId}")
    public List<Category> getCategoriesByGod(@PathVariable Long godId){
        Optional<God> god = godService.getGodById(godId);
        return god.map(categoryService::getCategoriesByGod).orElse(null);
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
