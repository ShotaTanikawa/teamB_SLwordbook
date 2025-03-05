package com.example.slwordbook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.model.Category;
import com.example.slwordbook.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    //カテゴリー(個)取得メソッド
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category Not Found With id= " + id));
    }
}
