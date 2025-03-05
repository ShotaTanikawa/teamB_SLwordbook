package com.example.slwordbook.model;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@SQLRestriction("is_deleted IS false")
@Table(name = "users")
public class User {
    
    //ユーザーID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ユーザー名
    @NotBlank(message = "ユーザー名は必須です")
    @Size(min = 4, max = 30, message="ユーザー名は4から30文字までにして下さい")
    @Column(name = "name", nullable = false, unique = true)
    private String username;

    // パスワード
    @NotBlank(message = "パスワードは必須です")
    @Size(min = 8, max = 60, message="パスワードは8から60文字までにして下さい")
    @Pattern(regexp = "^[a-zA-Z0-9\\\\-_.$%/]+$", message = "半角英数と一部の記号（-、_、.$%/）のみしか使えません")
    @Column(nullable = false)
    private String password;

    //削除フラグ
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    //中間テーブル
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    //ユーザーが作成した単語
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Word> words;

    //ユーザーが作成した単語帳
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wordbook> wordbooks;

    public User() {
    }

    public User(Long id, String username, String password,
            boolean deleted, Set<Role> roles, List<Word> words, List<Wordbook> wordbooks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.deleted = deleted;
        this.roles = roles;
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
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Wordbook> getWordbooks() {
        return wordbooks;
    }

    public void setWordbooks(List<Wordbook> wordbooks) {
        this.wordbooks = wordbooks;
    }

    
}
