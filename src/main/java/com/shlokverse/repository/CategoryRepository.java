package com.shlokverse.repository;

import com.shlokverse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //marks this as a repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String name);
}
