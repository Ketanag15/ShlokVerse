package com.shlokverse.implementation;

import com.shlokverse.model.God;
import com.shlokverse.repository.GodRepository;
import com.shlokverse.service.GodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GodServiceImpl implements GodService {

    private final GodRepository godRepository;

    @Autowired
    public GodServiceImpl(GodRepository godRepository) {
        this.godRepository = godRepository;
    }

    @Override
    public List<God> getAllGods() {
        return godRepository.findAll();
    }

    @Override
    public Optional<God> getGodById(Long id) {
        return godRepository.findById(id);
    }

    @Override
    public God saveGod(God god) {
        return godRepository.save(god);
    }

    @Override
    public void deleteGod(Long id) {
        godRepository.deleteById(id);
    }
}
