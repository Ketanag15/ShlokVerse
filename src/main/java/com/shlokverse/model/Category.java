package com.shlokverse.model;

import jakarta.persistence.*;

import java.util.List;

@Entity  // Marks this as a table in the database
@Table(name = "categories")  // The table name is "categories"
public class Category {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;

    @Column(nullable = false, unique = true)  // Category name cannot be null
    private String name;

    // Each category is simply a type like Aarti, Chalisa, etc.
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lyrics> lyrics; // All lyrics that are of this category type.


    // Default Constructor (Required for JPA)
    public Category() {
    }

    // Constructor with Parameters
    public Category(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lyrics> getLyrics() {
        return lyrics;
    }

    public void setLyrics(List<Lyrics> lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lyricsCount=" + (lyrics != null ? lyrics.size() : 0) +
                '}';
    }
}
