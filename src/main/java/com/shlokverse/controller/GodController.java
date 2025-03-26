package com.shlokverse.controller;

import com.shlokverse.model.God;
import com.shlokverse.service.GodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gods")
public class GodController {
    private final GodService godService;

    @Autowired
    public GodController(GodService godService){
        this.godService = godService;
    }

    @GetMapping
    public List<God> getAllGods(){
        return godService.getAllGods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<God> getGodById(@PathVariable Long id){
        return godService.getGodById(id)
                .map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
