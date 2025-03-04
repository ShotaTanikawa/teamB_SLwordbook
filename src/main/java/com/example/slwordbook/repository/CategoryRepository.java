package com.example.slwordbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    //カテゴリーIDでカテゴリー情報を取得する
    Optional<Category> findByCategoryId(Long id);
    //カテゴリー名でカテゴリー情報を取得する
    Optional<Category> findByCategory(String name);
}
