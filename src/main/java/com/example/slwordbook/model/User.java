package com.example.slwordbook.model;

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
    @Column(nullable = false, unique = true)
    private String name;

    // パスワード
    @NotBlank(message = "パスワードは必須です")
    @Size(min = 8, max = 60, message="パスワードは8から60文字までにして下さい")
    @Pattern(regexp = "^[a-zA-Z0-9\\\\-_.$%/]+$", message = "半角英数と一部の記号（-、_、.$%/）のみしか使えません")
    @Column(nullable = false)
    private String password;

    //削除フラグ
    @Column(nullable = false)
    private boolean is_deleted;

    //中間テーブル
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(Long id, String name, String password, boolean is_deleted, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.is_deleted = is_deleted;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    
}
