package edu.nc.servicebus.datagrid.controller;

import edu.nc.servicebus.datagrid.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
@Transactional
public class UserController  {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional
    public String delete(int id) {
        try {
            userDao.delete(id);

        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(String login, String password,String email) {
        try {
            userDao.add(login, passwordEncoder.encode(password), email);

        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "User succesfully saved!";

    }
    @RequestMapping(value = "/allUsers")
    @ResponseBody
    @Transactional
    public String getAllUsers() {
        try {
            return userDao.getAll();

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return e.toString();
        }
    }

}