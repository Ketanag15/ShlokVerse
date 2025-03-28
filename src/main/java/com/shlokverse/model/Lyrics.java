package com.shlokverse.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lyrics")
public class Lyrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "god_id", nullable = false)
    private God god;

    public Lyrics() {
    }

    public Lyrics(String content, String title, Category category, God god) {
        this.content = content;
        this.title = title;
        this.category = category;
        this.god = god;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public God getGod(){
        return god;
    }

    public void setGod(God god){
        this.god = god;
    }

    @Override
    public String toString() {
        return "Lyrics{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + (content != null ? content.substring(0, Math.min(30, content.length())) + "..." : "null") + '\'' +
                ", category=" + (category != null ? category.getName() : "null") +
                ", god=" + (god != null ? god.getName() : "null")+
                '}';

    }
}
