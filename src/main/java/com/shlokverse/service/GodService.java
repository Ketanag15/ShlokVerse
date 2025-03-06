package com.shlokverse.service;

import com.shlokverse.model.God;

import java.util.List;
import java.util.Optional;

public interface GodService {
    List<God> getAllGods();
    Optional<God> getGodById(Long id);
    God saveGod(God god);
    void deleteGod(Long id);

}
