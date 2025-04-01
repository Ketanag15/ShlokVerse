package com.shlokverse.dataloader;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.CategoryRepository;
import com.shlokverse.repository.GodRepository;
import com.shlokverse.repository.LyricsRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class Dataloader implements CommandLineRunner {
    private final GodRepository godRepository;
    private final CategoryRepository categoryRepository;
    private final LyricsRepository lyricsRepository;


    public Dataloader(GodRepository godRepository, CategoryRepository categoryRepository, LyricsRepository lyricsRepository){
        this.godRepository = godRepository;
        this.categoryRepository = categoryRepository;
        this.lyricsRepository = lyricsRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

    }

    private void insertData(String godName, String categoryName, String lyricsContent, String lyricsTitle){
        God god = godRepository.findByGodName(godName)
                .orElseGet(() ->  godRepository.save(new God(godName)));

        Category category = categoryRepository.findByCategoryName(categoryName)
                            .orElseGet(() -> categoryRepository.save(new Category((categoryName))));

        Optional<Lyrics> existingLyrics = lyricsRepository.findByGodAndCategory(god, category);
        if(existingLyrics.isEmpty()){
            Lyrics lyrics = new Lyrics();
            lyrics.setGod(god);
            lyrics.setCategory(category);
            lyrics.setContent(lyricsContent);
            lyrics.setTitle(lyricsTitle);
            lyricsRepository.save(lyrics);
            System.out.println("Inserted : " + lyricsTitle);
        } else{
            System.out.println("Skipped duplicate : " + lyricsTitle);
        }
    }
}
