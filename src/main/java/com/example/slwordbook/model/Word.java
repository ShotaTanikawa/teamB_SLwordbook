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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@SQLRestriction("is_deleted IS false")
@Table(name = "words")
public class Word {
    
    //単語ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //単語名
    @NotBlank(message = "単語名は必須です")
    @Size(max = 30, message="単語名は50文字までにして下さい")
    @Column(nullable = false)
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
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //削除フラグ
    @Column(nullable = false)
    private boolean is_deleted;

    //単語が所属するカテゴリー
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "カテゴリーの選択は必須です")
    private Category category;

    //ユーザーが作成した単語かどうかを識別
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    //中間エンティティ
    @OneToMany(mappedBy = "word")
    private List<WordbookWord> wordbookWords;


    public Word() {
    }

    public Word(Long id, String name, String describe, String detail_describe, LocalDateTime createdAt,
            LocalDateTime updatedAt, boolean is_deleted, Category category,
            User user, List<WordbookWord> wordbookWords) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.detail_describe = detail_describe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.is_deleted = is_deleted;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
