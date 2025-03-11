package com.shlokverse.implementation;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.LyricsRepository;
import com.shlokverse.service.LyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LyricsServiceImpl implements LyricsService {

    private final LyricsRepository lyricsRepository;

    @Autowired
    public LyricsServiceImpl(LyricsRepository lyricsRepository) {
        this.lyricsRepository = lyricsRepository;
    }

    @Override
    public List<Lyrics> getAllLyrics() {
        return lyricsRepository.findAll();
    }

    @Override
    public List<Lyrics> getLyricsByCategory(Category category) {
        return lyricsRepository.findByCategory(category);
    }

    @Override
    public Optional<Lyrics> getLyricsById(Long id) {
        return lyricsRepository.findById(id);
    }

    @Override
    public Lyrics saveLyrics(Lyrics lyrics) {
        return lyricsRepository.save(lyrics);
    }

    @Override
    public void deleteLyrics(Long id) {
        lyricsRepository.deleteById(id);
    }
}
