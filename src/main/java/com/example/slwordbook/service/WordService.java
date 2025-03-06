package com.example.slwordbook.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slwordbook.model.Category;
import com.example.slwordbook.model.Word;
import com.example.slwordbook.repository.WordRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;
    
    //カテゴリー付属する単語全取得メソッド
    public List<Word> findAllWordsByCategory(Long categoryId) {
        return wordRepository.findByCategoryId(categoryId);
    }

    //単語(個)取得メソッド
    public Word findWordById(Long id) {
        return wordRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Word Not Found With id= " + id));
    }

    //検索（単語名）メソッド
    public List<Word> search(String keyword, Long categoryId) {
        return wordRepository.search(keyword, categoryId);
    }

    //保存メソッド
    @Transactional
    public Word save(Word word) {
        word.setCreatedAt(LocalDateTime.now());
        return wordRepository.save(word);
    }

    //更新メソッド
    @Transactional
    public Word update(Word updateWord) {

        Word word = findWordById(updateWord.getId());
        //カテゴリーID用
        Category category = word.getCategory();

        word.setName(updateWord.getName());
        word.setDescribe(updateWord.getDescribe());
        word.setDetail_describe(updateWord.getDetail_describe());
        word.setUpdatedAt(LocalDateTime.now());
        //カテゴリーID用
        word.setCategory(category);

        return wordRepository.save(word);
    }

    //削除(論理削除)メソッド
    public void deleteWordById(Long id) {
        Optional<Word> optionalWord = wordRepository.findById(id);
        if (optionalWord.isPresent()) {
            Word word = optionalWord.get();
            word.setIs_deleted(true);
            wordRepository.save(word);
        }
    }
}
