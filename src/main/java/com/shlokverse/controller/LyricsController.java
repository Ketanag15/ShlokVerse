package com.shlokverse.controller;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.model.Lyrics;
import com.shlokverse.service.CategoryService;
import com.shlokverse.service.GodService;
import com.shlokverse.service.LyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lyrics")
public class LyricsController {
    private final LyricsService lyricsService;
    private final CategoryService categoryService;
    private final GodService godService;

    @Autowired
    public LyricsController(LyricsService lyricsService, CategoryService categoryService, GodService godService) {
        this.lyricsService = lyricsService;
        this.categoryService = categoryService;
        this.godService = godService;
    }

    @GetMapping
    public ResponseEntity<List<Lyrics>> getAllLyrics(){
        List<Lyrics> lyricsList = lyricsService.getAllLyrics();
        return ResponseEntity.ok(lyricsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lyrics> getLyricsById(@PathVariable Long id){
        return lyricsService.getLyricsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Lyrics>> getLyricsByCategory(@PathVariable Long categoryId){
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if(categoryOpt.isPresent()){
            List<Lyrics> lyricsList = lyricsService.getLyricsByCategory(categoryOpt.get());
            return ResponseEntity.ok(lyricsList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get lyrics by god id
    @GetMapping("/god/{godId}")
    public ResponseEntity<List<Lyrics>> getLyricsByGod(@PathVariable Long godId){
        Optional<God> godOpt = godService.getGodById(godId);
        if(godOpt.isPresent()){
            List<Lyrics> lyricsList = lyricsService.getLyricsByGod(godOpt.get());
            return ResponseEntity.ok(lyricsList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get lyrics by god id and category id
    @GetMapping("/god/{godId}/category/{categoryId}")
    public ResponseEntity<List<Lyrics>> getLyricsByGodAndCategory(@PathVariable Long godId, @PathVariable Long categoryId) {
        Optional<God> godOpt = godService.getGodById(godId);
        Optional<Category> categoryOpt = categoryService.getCategoryById(categoryId);
        if(godOpt.isPresent() && categoryOpt.isPresent()){
            List<Lyrics> lyricsList = lyricsService.getLyricsByGodAndCategory(godOpt.get(), categoryOpt.get());
            return ResponseEntity.ok(lyricsList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
