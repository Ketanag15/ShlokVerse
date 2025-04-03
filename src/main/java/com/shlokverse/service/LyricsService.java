package com.shlokverse.service;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;
import java.util.List;

public interface LyricsService {
    // For Page 2: Get categories available for a god
    List<Category> getCategoriesByGodId(Long godId);

    // For Page 3: Get lyrics for a god + category pair
    List<Lyrics> getLyricsByGodAndCategory(Long godId, Long categoryId);
}