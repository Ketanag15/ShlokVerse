package com.shlokverse.controller;

import com.shlokverse.model.God;
import com.shlokverse.service.GodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gods")
public class GodController{

    private final GodService godService;

    public GodController(GodService godService) {
        this.godService = godService;
    }

    @GetMapping
    public ResponseEntity<List<God>> getAllGods(){
        return ResponseEntity.ok(godService.getAllGods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<God> getGodById(@PathVariable Long godId){
        return godService.getGodById(godId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<God> getGodByName(@RequestParam String godName){
        return godService.findByGodName(godName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

