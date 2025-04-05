package com.shlokverse.controller;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.LyricsRepository;
import com.shlokverse.service.LyricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LyricsController {
    private final LyricsService lyricsService;

    public LyricsController(LyricsService lyricsService){
        this.lyricsService = lyricsService;
    }

    @GetMapping("/gods/{godId}/categories")
    public ResponseEntity<List<Category>> getCategoriesByGodId(@PathVariable Long godId){
        List<Category> categories = lyricsService.getCategoriesByGodId(godId);
        if(categories.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/god/{godId}/category/{categoryId}/lyrics")
    public ResponseEntity<List<Lyrics>> getLyrics(@PathVariable Long godId, @PathVariable Long categoryId){
        List<Lyrics> lyrics = lyricsService.getLyricsByGodAndCategory(godId, categoryId);

        if(lyrics.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lyrics);
    }
}