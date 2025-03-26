package com.shlokverse.repository;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.model.Lyrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LyricsRepository extends JpaRepository<Lyrics, Long> {
    List<Lyrics> findByCategory(Category category);

    List<Lyrics> findByGod(God god);

    List<Lyrics> findByGodAndCategory(God god, Category category);
}
