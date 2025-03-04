package com.example.slwordbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.WordbookWord;
import com.example.slwordbook.model.WordbookWordId;

@Repository
public interface WordbookWordRepository extends JpaRepository<WordbookWord, WordbookWordId> {
    
}
