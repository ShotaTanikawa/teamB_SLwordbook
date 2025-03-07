package com.example.slwordbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.repository.WordbookRepository;

@Service
public class WordbookService {

    @Autowired
    private WordbookRepository wordbookRepository;
    
}
