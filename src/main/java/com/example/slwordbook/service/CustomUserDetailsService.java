package com.example.slwordbook.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.slwordbook.model.CustomUserDetails;
import com.example.slwordbook.model.User;
import com.example.slwordbook.repository.RoleRepository;
import com.example.slwordbook.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Set<GrantedAuthority> grantedRoles = roleRepository.findByUserId(user.getId())
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet());

        // return new org.springframework.security.core.userdetails.User(
        //     user.getUsername(),
        //     user.getPassword(),
        //     !user.isDeleted(),
        //     true, true, true, // accountNonExpired, credentialsNonExpired, accountNonLocked
        //     grantedRoles
        // );

        return new CustomUserDetails(user);
    }
    
}
