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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@SQLRestriction("updatedAt IS NULL AND is_deleted IS false")
@Table(name = "words")
public class Word {
    
    //単語ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //単語名
    @NotBlank(message = "単語名は必須です")
    @Size(max = 30, message="単語名は50文字までにして下さい")
    @Column(nullable = false, unique = true)
    private String name;

    //単語の意味
    @NotBlank(message = "単語の意味は必須です")
    @Size(max = 200, message="単語の意味は200文字までにして下さい")
    @Column(nullable = false)
    private String describe;

    //単語の説明
    @Size(max = 200, message="単語の説明は200文字までにして下さい")
    @Column
    private String detail_describe;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "words_wordbooks",
    joinColumns = @JoinColumn(name = "word_id"),
    inverseJoinColumns = @JoinColumn(name = "wordbooks_id")
    )
    private Set<Wordbook> wordbooks;

    public Word() {
    }

    public Word(Long id, String name, String describe, String detail_describe, LocalDateTime createdAt,
            LocalDateTime updatedAt, boolean is_deleted, Set<Category> categories, Set<User> users,
            Set<Wordbook> wordbooks) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.detail_describe = detail_describe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.is_deleted = is_deleted;
        this.categories = categories;
        this.users = users;
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

    public String getDetail_describe() {
        return detail_describe;
    }

    public void setDetail_describe(String detail_describe) {
        this.detail_describe = detail_describe;
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

    public Set<Wordbook> getWordbooks() {
        return wordbooks;
    }

    public void setWordbooks(Set<Wordbook> wordbooks) {
        this.wordbooks = wordbooks;
    }

    

}
