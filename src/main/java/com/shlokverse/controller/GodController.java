package com.shlokverse.controller;

import com.shlokverse.model.God;
import com.shlokverse.service.GodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gods")
public class GodController {
    private GodService godService;

    @Autowired
    public GodController(GodService godService){
        this.godService = godService;
    }

    @GetMapping
    public List<God> getAllGods(){
        return godService.getAllGods();
    }

    @GetMapping("/{id}")
    public Optional<God> getGodById(@PathVariable Long id){
        return godService.getGodById(id);
    }

    @PostMapping
    public God createGod(@RequestBody God god){
        return godService.saveGod(god);
    }

    @DeleteMapping("/{id}")
    public void deleteGodById(@PathVariable Long id){
        godService.deleteGod(id);
    }
}
