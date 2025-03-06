package com.example.slwordbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Wordbook;

@Repository
public interface WordbookRepository extends JpaRepository<Wordbook, Long> {
    
}
