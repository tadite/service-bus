package edu.nc.servicebus.model.security;

import edu.nc.servicebus.datagrid.dao.UserDao;
import edu.nc.servicebus.datagrid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Registration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    public Registration(){}

    public void addUser(User user){
        userDao.add(user.getLogin(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail());
    }

    public boolean checkUsername(String username){
        if (userDao.getUserByUsername(username) != null){
            return false;
        }
        return true;
    }

    public boolean checkEmail(String email){
        if (userDao.getUserByEmail(email) != null){
            return false;
        }
        return true;
    }
}
