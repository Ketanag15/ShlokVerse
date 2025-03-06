package com.shlokverse.controller;

import com.shlokverse.model.God;
import com.shlokverse.service.GodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public class GodController {
    @Autowired
    private GodService godService;

    @GetMapping
    public ResponseEntity<List<God>> getAllGods(){
        List<God> gods = godService.getAllGods();
        return ResponseEntity.ok(gods);
    }

    @GetMapping
    public ResponseEntity<God> getGodById(Long id){
        Optional<God> god = godService.getGodById(id);
        return god.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
