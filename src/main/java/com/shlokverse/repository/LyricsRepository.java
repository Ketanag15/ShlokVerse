package com.shlokverse.repository;

import com.shlokverse.model.Category;
import com.shlokverse.model.Lyrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LyricsRepository extends JpaRepository<Lyrics, Long> {

    // Page 2: Get distinct categories for a god (optimized to fetch only IDs/names)
    @Query("SELECT NEW com.shlokverse.model.Category(c.categoryId, c.categoryName) " +
            "FROM Lyrics l JOIN l.category c WHERE l.god.godId = :godId " +
            "GROUP BY c.categoryId, c.categoryName")
    List<Category> findCategoriesByGodId(@Param("godId") Long godId);

    // Page 3: Get lyrics for a god + category (with sorting)
    List<Lyrics> findByGodGodIdAndCategoryCategoryIdOrderByLyricsTitleAsc(
            Long godId,
            Long categoryId
    );

    // Bonus: Fetch a specific lyric (e.g., for reporting)
    Optional<Lyrics> findByLyricsIdAndGodGodId(Long lyricsId, Long godId);
}