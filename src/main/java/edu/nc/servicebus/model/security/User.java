package edu.nc.servicebus.model.security;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails{

    @JsonProperty("login")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public User(){}

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
