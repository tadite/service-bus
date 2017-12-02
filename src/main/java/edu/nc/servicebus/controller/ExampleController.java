package edu.nc.servicebus.controller;

import edu.nc.servicebus.form.MainForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleController {

    private String error;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String example(Model model){

        MainForm mainForm = new MainForm();
        model.addAttribute("mainForm", mainForm);

        return "example";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String example(Model model,
                          @ModelAttribute("mainForm")MainForm mainForm){

        String name = mainForm.getName();
        if (name.length() > 0 && name != null){
            model.addAttribute("name", name);
            return "hello";
        }

        error = "name field empty";
        model.addAttribute("error", error);
        return "example";
    }
}
