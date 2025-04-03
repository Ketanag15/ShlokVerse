package com.shlokverse.implementation;

import com.shlokverse.model.God;
import com.shlokverse.repository.GodRepository;
import com.shlokverse.service.GodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GodServiceImpl implements GodService{

    private final GodRepository godRepository;

    public GodServiceImpl(GodRepository godRepository){
        this.godRepository = godRepository;
    }

    @Override
    public List<God> getAllGods() {
        return godRepository.findAll();
    }

    @Override
    public Optional<God> getGodById(Long godId) {
        return godRepository.findById(godId);
    }

    @Override
    public Optional<God> getGodByName(String godName) {
        return godRepository.findByGodName(godName);
    }
}