package com.shlokverse.service;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;

import java.util.List;
import java.util.Optional;

public interface LyricsService {
    List<Lyrics> getAllLyrics();
    List<Lyrics> getLyricsByCategory(Category category);
    Optional<Lyrics> getLyricsById(Long id);
    Lyrics saveLyrics(Lyrics lyrics);
    void deleteLyrics(Long id);

}
