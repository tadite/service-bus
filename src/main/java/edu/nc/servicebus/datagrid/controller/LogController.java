package edu.nc.servicebus.datagrid.controller;

import edu.nc.servicebus.datagrid.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/log")
@Transactional
public class LogController {

    @Autowired
    private LogDao logDao;

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional
    public String delete(int id) {
        try {
            logDao.delete(id);

        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Log successfully deleted!";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(int requestId, int responseId, int errorId) {
        try {
            logDao.add(requestId, responseId, errorId);

        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Log successfully saved!";
    }
    @RequestMapping(value = "/allLogs")
    @ResponseBody
    @Transactional
    public String getAllLogs() {
        try {
            return logDao.getAll();

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
            logDao.clear();
        } catch (Exception e){
            return e.getMessage();
        }
        return "Log table successfully cleared";
    }
}
