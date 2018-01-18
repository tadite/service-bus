package edu.nc.servicebus.model.action;

import edu.nc.servicebus.model.request.AppendRequestFilter;
import edu.nc.servicebus.model.request.HttpRequest;
import edu.nc.servicebus.model.request.RequestFilter;
import edu.nc.servicebus.model.response.JsonPathResponseFilter;
import edu.nc.servicebus.model.response.ResponseFilter;
import edu.nc.servicebus.model.sender.HttpSender;
import edu.nc.servicebus.model.source.RestSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MockActionFactory implements ActionFactory {

    //TODO: implement dao repositories
    private HashMap<String, Action> actionHashMap = new HashMap<>();
    {
        List<RequestFilter> requestFilterList = new LinkedList<>();
        requestFilterList.add(new AppendRequestFilter("/users"));

        List<ResponseFilter> responseFilters = new LinkedList<>();
        responseFilters.add(new JsonPathResponseFilter("$[*].email"));

        Action action = new HttpAction(new HttpRequest(new RestSource("https://jsonplaceholder.typicode.com")),
                new HttpSender(), requestFilterList, responseFilters);

        actionHashMap.put("jsonplaceholder_users_email-list", action);

        //https://api.vk.com/method/database.getCountries?need_all=1&count=250
        List<RequestFilter> requestFilterListVk = new LinkedList<>();
        requestFilterListVk.add(new AppendRequestFilter("/method"));
        requestFilterListVk.add(new AppendRequestFilter("/database.getCountries"));
        requestFilterListVk.add(new AppendRequestFilter("?need_all=1"));
        requestFilterListVk.add(new AppendRequestFilter("&count=250"));

        List<ResponseFilter> responseFiltersVk = new LinkedList<>();
        responseFiltersVk.add(new JsonPathResponseFilter("$.response[*]"));

        actionHashMap.put("vk_db_countries", new HttpAction(new HttpRequest(new RestSource("https://api.vk.com")),
                new HttpSender(), requestFilterListVk, responseFiltersVk));

    }

    @Override
    public Action getAction(String name) {
        return actionHashMap.get(name);
    }

    @Override
    public Collection<String> getActionNames() {
        return actionHashMap.keySet();
    }
}
