package com.example.slwordbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slwordbook.model.User;
import com.example.slwordbook.model.Wordbook;

@Repository
public interface WordbookRepository extends JpaRepository<Wordbook, Long> {
    List<Wordbook> findByUser(User user);
}
