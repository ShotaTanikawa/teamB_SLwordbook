package com.example.slwordbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Word;

@Repository
public interface WordbookRepository extends JpaRepository<Word, Long> {
    
}
