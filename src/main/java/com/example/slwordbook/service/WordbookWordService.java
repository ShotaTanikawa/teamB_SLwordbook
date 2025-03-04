package com.example.slwordbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.repository.WordbookWordRepository;

@Service
public class WordbookWordService {
    
    @Autowired
    private WordbookWordRepository wordbookWordRepository;
}
