package com.shlokverse.repository;

import com.shlokverse.model.God;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GodRepository extends JpaRepository<God, Long> {
    Optional<God> getGodByName(String godName); // Safer null handling
}