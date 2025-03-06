package com.example.slwordbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
