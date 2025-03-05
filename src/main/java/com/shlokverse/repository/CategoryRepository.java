package com.shlokverse.repository;

import com.shlokverse.model.Category;
import com.shlokverse.model.God;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //marks this as a repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByGod(God god);
}
