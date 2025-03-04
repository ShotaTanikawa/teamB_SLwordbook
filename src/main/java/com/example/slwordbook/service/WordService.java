package com.example.slwordbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;
    
}
