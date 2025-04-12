package com.shlokverse.dataloader;

import com.shlokverse.constants.LyricsContent;
import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.CategoryRepository;
import com.shlokverse.repository.GodRepository;
import com.shlokverse.repository.LyricsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Dataloader implements CommandLineRunner {
    private final GodRepository godRepository;
    private final CategoryRepository categoryRepository;
    private final LyricsRepository lyricsRepository;
    private static final Logger log = LoggerFactory.getLogger(Dataloader.class);

    public Dataloader(GodRepository godRepository, CategoryRepository categoryRepository, LyricsRepository lyricsRepository){
        this.godRepository = godRepository;
        this.categoryRepository = categoryRepository;
        this.lyricsRepository = lyricsRepository;
    }

    private static final List<String[]> DATA_CONTENT = List.of(
            new String[]{"Shiv", "Aarti", LyricsContent.SHIV_AARTI, "Shiv Aarti"},
            new String[]{"Hanuman", "Aarti", LyricsContent.HANUMAN_AARTI, "Hanuman Aarti"},
            new String[]{"Hanuman", "Chalisa", LyricsContent.HANUMAN_CHALISA, "Hanuman Chalisa"},
            new String[]{"Shiv", "Chalisa", LyricsContent.SHIV_CHALISA, "Shiv Chalisa"},
            new String[]{"Vishnu", "Aarti", LyricsContent.VISHNU_AARTI, "Vishnu Aarti"}
            );

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("Data Loading Started ...");

        DATA_CONTENT.forEach(data ->
                insertData(data[0], data[1], data[2], data[3])
        );

        System.out.println("Data successfully loaded !!");
    }

    private void insertData(String godName, String categoryName, String lyricsContent, String LyricsTitle){

        if (godName == null || categoryName == null || lyricsContent == null || LyricsTitle == null) {
            throw new IllegalArgumentException("Data fields cannot be null!");
        }

        //Find or Create God
        God god = godRepository.findByGodName(godName)
                    .orElseGet(() ->{
                        God newGod = godRepository.save(new God(godName));
                        log.info("New God Saved: {}", godName);
                        return newGod;
                    });

        //Find or create the Category
        Category category = categoryRepository.findByCategoryName(categoryName)
                                .orElseGet(() -> {
                                    Category newCategory = categoryRepository.save(new Category(categoryName));
                                    log.info("New Category Saved: {}", categoryName);
                                    return newCategory;
                                });

        //check if the lyrics exists in DB
        boolean doesLyricsExists = !lyricsRepository.findByGodGodIdAndCategoryCategoryIdOrderByLyricsTitleAsc(
                            god.getGodId(),
                            category.getCategoryId())
                            .isEmpty();

        //if lyrics doesn't exist, then save
        if(!doesLyricsExists){
            Lyrics lyrics = new Lyrics(god, category, LyricsTitle, lyricsContent);
            lyricsRepository.save(lyrics);
            log.info("Added Lyrics: " + LyricsTitle);
        } else {
            log.info("Skipped existing: " + godName + " - " + categoryName);
        }
    }
}