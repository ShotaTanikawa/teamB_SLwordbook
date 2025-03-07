package com.example.slwordbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.model.User;
import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.model.WordbookWord;
import com.example.slwordbook.repository.WordbookWordRepository;

@Service
public class WordbookWordService {
    
    @Autowired
    private WordbookWordRepository wordbookWordRepository;

    //Get all Mywordbooks
    public List<WordbookWord> listAll() {
        return wordbookWordRepository.findAll();
    }

    public List<WordbookWord> findByWordbookId(Long wordbookId) {
        return wordbookWordRepository.findByWordbookId(wordbookId);
    };
}
