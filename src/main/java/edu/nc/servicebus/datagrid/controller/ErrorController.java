package edu.nc.servicebus.datagrid.controller;

import edu.nc.servicebus.datagrid.dao.ErrorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/error")
@Transactional
public class ErrorController {

    @Autowired
    private ErrorDao errorDao;

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional
    public String delete(int id) {
        try {
            errorDao.delete(id);

        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Error succesfully deleted!";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(String  reason) {
        try {
            errorDao.add(reason);

        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Error succesfully saved!";

    }
    @RequestMapping(value = "/allErrors")
    @ResponseBody
    @Transactional
    public String getAllErrors() {
        try {
            return errorDao.getAll();

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return e.toString();
        }
    }
}
