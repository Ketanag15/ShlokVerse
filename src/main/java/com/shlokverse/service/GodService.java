package com.shlokverse.service;

import com.shlokverse.model.God;
import java.util.List;
import java.util.Optional;

public interface GodService {
    List<God> getAllGods();
    Optional<God> getGodById(Long godId);
    Optional<God> findByGodName(String godName);
}