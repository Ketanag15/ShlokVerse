package com.shlokverse.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Lyrics")
public class Lyrics{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lyrics_id")
    private Long lyricsId;

    // Many-to-One: Many Lyrics can belong to one God
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "god_id", nullable = false)
    private God god;

    // Many-to-One: Many Lyrics can belong to one Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "Lyrics_Title", nullable = false)
    private String lyricsTitle;

    @Column(name = "Lyrics_Content", nullable = false, columnDefinition = "TEXT")
    private String lyricsContent;


    public Lyrics(){

    }

    public Lyrics(God god, Category category, String lyricsTitle, String lyricsContent) {
        this.god = god;
        this.category = category;
        this.lyricsTitle = lyricsTitle;
        this.lyricsContent = lyricsContent;
    }

    public Long getLyricsId() {
        return lyricsId;
    }

    public God getGod() {
        return god;
    }

    public void setGod(God god) {
        this.god = god;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLyricsTitle() {
        return lyricsTitle;
    }

    public void setLyricsTitle(String lyricsTitle) {
        this.lyricsTitle = lyricsTitle;
    }

    public String getLyricsContent() {
        return lyricsContent;
    }

    public void setLyricsContent(String lyricsContent) {
        this.lyricsContent = lyricsContent;
    }
}