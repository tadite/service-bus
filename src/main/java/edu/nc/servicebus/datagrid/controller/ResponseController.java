package edu.nc.servicebus.datagrid.controller;

import edu.nc.servicebus.datagrid.dao.ResponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/response")
@Transactional
public class ResponseController {

    @Autowired
    private ResponseDao responseDao;

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional
    public String delete(int id) {
        try {
            responseDao.delete(id);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Response successfully deleted!";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(String  reason) {
        try {
            //responseDao.add(reason);

        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Response successfully saved!";

    }
    @RequestMapping(value = "/allResponses")
    @ResponseBody
    @Transactional
    public String getAllResponses() {
        try {
            return responseDao.getAll();

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return e.toString();
        }
    }

    @RequestMapping(value = "/clear")
    @ResponseBody
    @Transactional
    public String clear(){
        try{
            responseDao.clear();
        } catch (Exception e){
            return e.getMessage();
        }
        return "Response table successfully cleared";
    }
}