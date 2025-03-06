package com.example.slwordbook.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@SQLRestriction("is_deleted IS false")
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

    //ユーザーが作成した単語帳
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 中間エンティティ
    @OneToMany(mappedBy = "wordbook")
    private List<WordbookWord> wordbookWords;

    public Wordbook() {
    }

public Wordbook(Long id, String name, String describe, LocalDateTime createdAt,
            LocalDateTime updatedAt, boolean is_deleted, User user, List<WordbookWord> wordbookWords) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.is_deleted = is_deleted;
        this.user = user;
        this.wordbookWords = wordbookWords;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WordbookWord> getWordbookWords() {
        return wordbookWords;
    }

    public void setWordbookWords(List<WordbookWord> wordbookWords) {
        this.wordbookWords = wordbookWords;
    }

    
}
