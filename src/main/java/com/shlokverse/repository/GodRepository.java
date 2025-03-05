package com.shlokverse.repository;

import com.shlokverse.model.God;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodRepository extends JpaRepository<God, Long> {
    //Crud operations
}

