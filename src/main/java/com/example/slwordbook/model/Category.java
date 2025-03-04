package com.example.slwordbook.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@SQLRestriction("updatedAt IS NULL AND is_deleted IS false AND opened IS false")
@Table(name = "categories")
public class Category {
    
    //カテゴリーID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //カテゴリー名
    @NotBlank(message = "カテゴリー名は必須です")
    @Size(max = 30, message="カテゴリー名は30文字までにして下さい")
    @Column(nullable = false, unique = true)
    private String name;

    //カテゴリーの説明
    @Size(max = 200, message="カテゴリーの説明は200文字までにして下さい")
    @Column
    private String describe;

    //登録日時
    @Column(nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column
    private LocalDateTime updatedAt;

    //公開フラグ
    @Column(nullable = false)
    private boolean opened;

    //削除フラグ
    @Column(nullable = false)
    private boolean is_deleted;

    //中間テーブル
    @ManyToMany(mappedBy = "categories")
    private Set<Word> words;

    //中間テーブル
    @ManyToMany(mappedBy = "categories")
    private Set<Wordbook> wordbooks;

    public Category() {
    }

    public Category(Long id, String name, String describe, LocalDateTime createdAt,
            LocalDateTime updatedAt, boolean opened, boolean is_deleted, Set<Word> words, Set<Wordbook> wordbooks) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.opened = opened;
        this.is_deleted = is_deleted;
        this.words = words;
        this.wordbooks = wordbooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public Set<Wordbook> getWordbooks() {
        return wordbooks;
    }

    public void setWordbooks(Set<Wordbook> wordbooks) {
        this.wordbooks = wordbooks;
    }

    

}
