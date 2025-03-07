package com.example.slwordbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    //カテゴリーとユーザーに対応した単語取得用
    @Query("SELECT w FROM Word w WHERE w.category.id = :categoryId AND (w.user.id = :userId OR w.user.id IS NULL)")
    List<Word> findAllWordsByCategoryIdAndUserId(@Param("categoryId") Long categoryId, @Param("userId") Long userId);
    
    //あいまい検索機能(大小区別)
    // @Query("SELECT w FROM Word w WHERE w.name LIKE %?1% AND w.category.id = ?2")
    // List<Word> search(String keyword, Long categoryId);

    //あいまい検索機能
    @Query("SELECT w FROM Word w WHERE LOWER(w.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND w.category.id = :categoryId AND (w.user.id = :userId OR w.user.id IS NULL)")
    List<Word> searchAndUserId(@Param("keyword") String keyword, @Param("categoryId") Long categoryId, @Param("userId") Long userId);
}
