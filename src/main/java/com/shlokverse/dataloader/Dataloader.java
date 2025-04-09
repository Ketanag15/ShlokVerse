package com.shlokverse.dataloader;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import com.shlokverse.model.Lyrics;
import com.shlokverse.repository.CategoryRepository;
import com.shlokverse.repository.GodRepository;
import com.shlokverse.repository.LyricsRepository;

public class Dataloader{
    private final GodRepository godRepository;
    private final CategoryRepository categoryRepository;
    private final LyricsRepository lyricsRepository;

    public Dataloader(GodRepository godRepository, CategoryRepository categoryRepository, LyricsRepository lyricsRepository){
        this.godRepository = godRepository;
        this.categoryRepository = categoryRepository;
        this.lyricsRepository = lyricsRepository;
    }


    private void insertData(String godName, String categoryName, String lyricsContent, String LyricsTitle){

        //Find or Create God
        God god = godRepository.findByGodName(godName)
                    .orElseGet(() ->{
                        God newGod = godRepository.save(new God(godName));
                        System.out.println("New God Saved : " +godName);
                        return newGod;
                    });

        //Find or create the Category
        Category category = categoryRepository.findByCategoryName(categoryName)
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