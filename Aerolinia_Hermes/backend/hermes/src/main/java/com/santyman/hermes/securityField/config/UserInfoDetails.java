package com.santyman.hermes.securityField.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.santyman.hermes.entities.User;

public class UserInfoDetails implements UserDetails{

    private String email;

    private String password;

    private List<GrantedAuthority> authorities;

    

    public UserInfoDetails(User user) {
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.authorities=Arrays.stream(user.getRole().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
       return email;
    }

    
}
