package edu.nc.servicebus.controller.database;

import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.model.Request;
import edu.nc.servicebus.statistics.RequestData;
import edu.nc.servicebus.statistics.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/statistics")
public class RequestStatsController {

    @Autowired
    private RequestDao requestDao;

    @RequestMapping(value = "/requestPerSecond")
    public ResponseEntity requestsPerSecond(){
        return ResponseEntity.ok(getRequestDataList(1, 1, 1));
    }

    @RequestMapping(value = "/requestPerMinute")
    public ResponseEntity requestPerMinute(){
        return ResponseEntity.ok(getRequestDataList(1, 1, 60));
    }

    @RequestMapping(value = "/requestPerHour")
    public ResponseEntity requestPerHour(){
        return ResponseEntity.ok(getRequestDataList(1, 60, 60));
    }

    @RequestMapping(value = "/requestPerDay")
    public ResponseEntity requestPerDay(){
        return ResponseEntity.ok(getRequestDataList(24, 60, 60));
    }

    private List<RequestData> getRequestDataList(int hour, int minute, int second){
        List<Request> requests = requestDao.getRequestList();
        Statistic stats = new Statistic();
        List<String> contents = new ArrayList<>();
        long prevTime = requests.get(0).getTime().getTime() / (1000 * hour * minute * second);
        long time = 0;

        for (Request request: requests){
            time = request.getTime().getTime() / (1000 * hour * minute * second);

            if ((time - prevTime) > 0 || request.getRequestId() == requests.get(requests.size() - 1).getRequestId()){
                stats.add(prevTime * (1000 * hour * minute * second), contents);
                prevTime = time;
                contents.clear();
            }
            contents.add(request.getContent());
        }

        return stats.getRequestData();
    }
}
