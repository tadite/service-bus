package edu.nc.servicebus.controller;

import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.dao.ResponseDao;
import edu.nc.servicebus.model.action.ActionFactory;
import edu.nc.servicebus.model.executor.Executor;
import edu.nc.servicebus.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


@RestController
public class EndpointController {

    @Autowired
    Executor executor;

    @Autowired
    ActionFactory actionFactory;

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private ResponseDao responseDao;

    @RequestMapping(value = "/endpoint/**", method = RequestMethod.GET)
    @ResponseBody
    public String endpoint(HttpServletRequest request){
        //String endpointName = Util.parseEndpointName(request);
        String endpointName = request.getRequestURI().split("endpoint/")[1];
        String endpointUrl = endpointName + "?" + request.getQueryString();

        requestDao.add(endpointUrl);

        Response response = executor.executeAction(endpointUrl);

        responseDao.add(response.getRawData());

        return response.getRawData();
    }

    //@RequestMapping(value = "/endpoint/{endpoint-name}", method = RequestMethod.GET)
    //@ResponseBody
    public String endpoint(@PathVariable("endpoint-name") String endpointName){
        //String endpointName = Util.parseEndpointName(request);

        Response response = executor.executeAction(endpointName);

        return response.getRawData();
    }

    @RequestMapping(value = "/endpoint-list", method = RequestMethod.GET)
    @ResponseBody
    public Collection<String> endpointList(){
        return actionFactory.getActionNames();
    }

}
