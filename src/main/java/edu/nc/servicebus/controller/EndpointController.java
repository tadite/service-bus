package edu.nc.servicebus.controller;

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

    @RequestMapping(value = "/endpoint/**", method = RequestMethod.GET)
    @ResponseBody
    public String endpoint(HttpServletRequest request){
        //String endpointName = Util.parseEndpointName(request);
        String endpointName = request.getRequestURI().split("endpoint/")[1];
        String endpointUrl = endpointName + "?" + request.getQueryString();

        Response response = executor.executeAction(endpointUrl);

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
