package com.example.slwordbook.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.slwordbook.exception.MyUniqueConstraintViolationException;
import com.example.slwordbook.model.Role;
import com.example.slwordbook.model.User;
import com.example.slwordbook.repository.RoleRepository;
import com.example.slwordbook.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //ユーザー新規登録機能
    public User addDeletedUserAndHashPassword(User user) {
        //名前のユニーク性違反チェック
        User uniqueUser = userRepository.findByUsername(user.getUsername());
        //既に存在する場合は自作のExceptionをthrow
        if (uniqueUser != null) {
            throw new MyUniqueConstraintViolationException("既に存在するユーザー名です");
        }
        //削除フラグをfalseに
        user.setDeleted(false);
        //ハッシュ化するクラスの準備
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //ハッシュ化
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        //ハッシュ化されたパスワードを詰めなおします
        user.setPassword(hashedPassword);

        Role role = roleRepository.findByname("ROLE_USER")
        .orElseThrow(() -> new EntityNotFoundException("Role Not Found with name=USER"));

        //ユーザーのロールをセット
        user.setRoles(Set.of(role));

        return userRepository.save(user);
    }
}
