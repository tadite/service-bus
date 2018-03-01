package edu.nc.servicebus.datagrid.controller;

import edu.nc.servicebus.datagrid.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/request")
@Transactional
public class RequestController {

    @Autowired
    private RequestDao requestDao;

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional
    public String delete(int id) {
        try {
            requestDao.delete(id);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Request successfully deleted!";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(String  reason) {
        try {
            //requestDao.add(reason);

        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Request successfully saved!";

    }
    @RequestMapping(value = "/allRequests")
    @ResponseBody
    @Transactional
    public String getAllRequests() {
        try {
            return requestDao.getAll();

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
            requestDao.clear();
        } catch (Exception e){
            return e.getMessage();
        }
        return "Request table successfully cleared";
    }
}