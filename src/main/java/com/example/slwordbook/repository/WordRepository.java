package com.example.slwordbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    
    List<Word> findByWord(String name);
}
