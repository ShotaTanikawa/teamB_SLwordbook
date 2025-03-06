package com.example.slwordbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.repository.WordbookRepository;

@Service
public class WordbookService {

    @Autowired
    private WordbookRepository wordbookRepository;
    
    //Get all Mywordbooks
    public List<Wordbook> listAll(Long id) {
        return wordbookRepository.findByUserId(id);
    }
}
