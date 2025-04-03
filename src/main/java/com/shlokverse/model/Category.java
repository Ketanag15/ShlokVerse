package com.shlokverse.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Category")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_Name", nullable = false, unique = true)
    private String categoryName;

    // One-to-Many: One Category can have many Lyrics entries
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lyrics> lyrics;

    public Category(){

    }

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public Long getCategoryId(){
        return categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public List<Lyrics> getLyrics(){
        return lyrics;
    }

    public void setLyrics(List<Lyrics> lyrics){
        this.lyrics = lyrics;
    }
}