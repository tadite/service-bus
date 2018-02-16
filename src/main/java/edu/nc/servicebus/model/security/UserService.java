package edu.nc.servicebus.model.security;

import edu.nc.servicebus.datagrid.dao.UserDao;
import edu.nc.servicebus.datagrid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;

import java.util.HashSet;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(s);

        if (user == null){
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), new HashSet<GrantedAuthority>());
    }


}
