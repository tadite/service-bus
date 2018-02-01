package edu.nc.servicebus.controller;

import edu.nc.servicebus.form.MainForm;
import edu.nc.servicebus.model.security.Registration;
import edu.nc.servicebus.model.security.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

//@Controller
public class ExampleController {

    private String error;

   /* @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String example(){

        return "hello";
    }

    //@RequestMapping(value = "/", method = RequestMethod.POST)
    public String example(Model model,
                          @ModelAttribute("mainForm")MainForm mainForm){

        String name = mainForm.getLogin();
        if (name.length() > 0 && name != null){
            model.addAttribute("login", name);
            return "hello";
        }

        error = "name field empty";
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model){

        if (logout != null) {
            model.addAttribute("logout", "You've been logged out!");
        }
        if (error != null) {
            model.addAttribute("error", "Invalid Login or Password");
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistration(Model model){

        User user = new User();
        model.addAttribute("newUser", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String getRegistration(Model model,
                                  @ModelAttribute("newUser")User user){

        String login = user.getLogin();
        String password = user.getPassword();
        String error;

        if (login.length() == 0 || password.length() == 0){
            error = "Some field is empty";
            model.addAttribute("error", error);
            return "/registration";
        }

        Registration registration = new Registration();
        registration.addUser(user);

        return "/hello";
    }*/

    @RequestMapping(value = "/")
    public String getLogin(){
        return "index.html";
    }
}
