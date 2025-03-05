package com.shlokverse.model;

import jakarta.persistence.*;
import java.util.List;

@Entity //this marks it as a table in the database.
@Table(name = "gods") //specifies the table name as "gods".
public class God {
    @Id //Mark the ID as primary key of the gods table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //For Auto Incrementing the IDs.
    private Long id;

    @Column(nullable = false, unique = true) //Ensures no duplicate name of gods.
    private String name;

    @OneToMany(mappedBy = "god", cascade = CascadeType.ALL) //One god can have multiple categories like aarti, chalisa shlok.
    private List<Category> categories;

    public God() { }

    public God(String name){
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
