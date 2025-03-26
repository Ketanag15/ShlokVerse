package com.shlokverse.controller;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.service.CategoryService;
import com.shlokverse.service.GodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
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

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id)
                .map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
