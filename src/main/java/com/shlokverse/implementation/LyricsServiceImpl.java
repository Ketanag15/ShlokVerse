package com.shlokverse.implementation;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.LyricsRepository;
import com.shlokverse.service.LyricsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LyricsServiceImpl implements LyricsService {

    private final LyricsRepository lyricsRepository;

    public LyricsServiceImpl(LyricsRepository lyricsRepository) {
        this.lyricsRepository = lyricsRepository;
    }

    @Override
    public List<Category> getCategoriesByGodId(Long godId) {
        return lyricsRepository.findCategoriesByGodId(godId);
    }

    @Override
    public List<Lyrics> getLyricsByGodAndCategory(Long godId, Long categoryId) {
        return lyricsRepository.findByGodGodIdAndCategoryCategoryIdOrderByLyricsTitleAsc(
                godId,
                categoryId
        );
    }
}