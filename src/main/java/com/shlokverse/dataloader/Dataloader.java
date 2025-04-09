package com.shlokverse.dataloader;

import com.shlokverse.constants.LyricsContent;
import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.CategoryRepository;
import com.shlokverse.repository.GodRepository;
import com.shlokverse.repository.LyricsRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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
        System.out.println("🚀 Starting data loading...");

        DATA_CONTENT.forEach(data ->
                insertData(data[0], data[1], data[2], data[3])
        );

        System.out.println("✅ Data loading completed!");
    }

    private void insertData(String godName, String categoryName, String lyricsContent, String LyricsTitle){

        //Find or Create God
        God god = godRepository.getGodByName(godName)
                    .orElseGet(() ->{
                        God newGod = godRepository.save(new God(godName));
                        System.out.println("New God Saved : " +godName);
                        return newGod;
                    });

        //Find or create the Category
        Category category = categoryRepository.getCategoryByName(categoryName)
                                .orElseGet(() -> {
                                    Category newCategory = categoryRepository.save(new Category(categoryName));
                                    System.out.println("New Category saved : " +categoryName);
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
            System.out.println("Added Lyrics: " + LyricsTitle);
        } else {
            System.out.println("Skipped existing: " + godName + " - " + categoryName);
        }
    }
}