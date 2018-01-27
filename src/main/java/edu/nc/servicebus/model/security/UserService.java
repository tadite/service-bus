package edu.nc.servicebus.model.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private JsonUserReader userReader;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userReader.getUsersByLogin(s);

        if (user != null) {
            Set<GrantedAuthority> grant = new HashSet<>();
            grant.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grant);
        }

        return null;
    }
}
