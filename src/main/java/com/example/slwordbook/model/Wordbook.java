package com.example.slwordbook.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@SQLRestriction("updatedAt IS NULL AND is_deleted IS false")
@Table(name = "wordbooks")
public class Wordbook {
    
    //単語帳ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //単語帳名
    @NotBlank(message = "単語帳名は必須です")
    @Size(max = 50, message="単語帳名は50文字までにして下さい")
    @Column(nullable = false, unique = true)
    private String name;

    //単語帳の説明
    @Size(max = 200, message="単語帳の説明は200文字までにして下さい")
    @Column
    private String describe;

    //登録日時
    @Column(nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column
    private LocalDateTime updatedAt;

    //削除フラグ
    @Column(nullable = false)
    private boolean is_deleted;

    //中間テーブル
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Category> categories;

    //中間テーブル
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<User> users;

    //中間テーブル
    @ManyToMany(mappedBy = "wordbooks")
    private Set<Word> words;

    public Wordbook() {
    }

    public Wordbook(Long id, String name, String describe, LocalDateTime createdAt,
            LocalDateTime updatedAt, boolean is_deleted, Set<Category> categories, Set<User> users, Set<Word> words) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.is_deleted = is_deleted;
        this.categories = categories;
        this.users = users;
        this.words = words;
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

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    
}
