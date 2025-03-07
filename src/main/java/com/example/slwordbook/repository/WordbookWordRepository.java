package com.example.slwordbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.User;
import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.model.WordbookWord;
import com.example.slwordbook.model.WordbookWordId;

@Repository
public interface WordbookWordRepository extends JpaRepository<WordbookWord, WordbookWordId> {
    List<WordbookWord> findByWordbookId(Long wordbookId);
}
