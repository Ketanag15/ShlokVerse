package com.shlokverse.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "God")
public class God{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "god_id")
    private Long godId;

    @Column(name = "god_Name", nullable = false, unique = true)
    private String godName;

    // One-to-Many: One God can have many Lyrics entries
    @OneToMany(mappedBy = "god", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lyrics> lyrics;

    public God(){

    }

    public God(String godName){
        this.godName = godName;
    }

    public Long getGodId(){
        return godId;
    }

    public String getGodName(){
        return godName;
    }

    public void setGodName(String godName){
        this.godName = godName;
    }

    public List<Lyrics> getLyrics(){
        return lyrics;
    }

    public void setLyrics(List<Lyrics> lyrics){
        this.lyrics = lyrics;
    }
}