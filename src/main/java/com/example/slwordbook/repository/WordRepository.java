package com.example.slwordbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    
    List<Word> findByCategoryId(Long categoryId);
    
    //あいまい検索機能(大小区別)
    // @Query("SELECT w FROM Word w WHERE w.name LIKE %?1% AND w.category.id = ?2")
    // List<Word> search(String keyword, Long categoryId);

    //あいまい検索機能
    @Query("SELECT w FROM Word w WHERE LOWER(w.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND w.category.id = :categoryId")
    List<Word> search(@Param("keyword") String keyword, @Param("categoryId") Long categoryId);

}
