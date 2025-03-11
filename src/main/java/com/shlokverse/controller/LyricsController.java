package com.shlokverse.controller;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;
import com.shlokverse.service.CategoryService;
import com.shlokverse.service.LyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lyrics")
public class LyricsController {
    private final LyricsService lyricsService;
    private final CategoryService categoryService;

    @Autowired
    public LyricsController(LyricsService lyricsService, CategoryService categoryService) {
        this.lyricsService = lyricsService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Lyrics> getAllLyrics(){
        return lyricsService.getAllLyrics();
    }

    @GetMapping("/{id}")
    public Optional<Lyrics> getLyricsById(@PathVariable Long id){
        return lyricsService.getLyricsById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Lyrics> getLyricsByCategory(@PathVariable Long categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        return category.map(lyricsService::getLyricsByCategory).orElse(null);
    }

    @PostMapping
    public Lyrics createLyrics(@RequestBody Lyrics lyrics){
        return lyricsService.saveLyrics(lyrics);
    }

    @DeleteMapping("/{id}")
    public void deleteLyrics(@PathVariable Long id){
        lyricsService.deleteLyrics(id);
    }

}
