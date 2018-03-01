package edu.nc.servicebus.controller.statistic;

import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.model.Request;
import edu.nc.servicebus.statistics.Content;
import edu.nc.servicebus.statistics.Statistic;
import edu.nc.servicebus.statistics.request.RequestContent;
import edu.nc.servicebus.statistics.request.RequestStatistic;
import edu.nc.servicebus.statistics.StatsData;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/statistics")
public class RequestStatsController {

    @Autowired
    private RequestDao requestDao;

    @Autowired
    @Qualifier("request")
    private ObjectFactory<RequestStatistic> requestStatistic;

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

    private List<StatsData> getRequestDataList(int hour, int minute, int second){
        List<Request> requests = requestDao.getRequestList();
        List<Content> contents = new ArrayList<>();

        Statistic stats = requestStatistic.getObject();

        long converter = 1000 * hour * minute * second;
        long prevTime = requests.get(0).getTime().getTime() / converter;
        long time = 0;

        for (Request request: requests){
            time = request.getTime().getTime() / converter;

            int requestTime = (int) (request.getEndTime().getTime() - request.getTime().getTime());
            Content requestContent = new RequestContent(requestTime, request.getContent());

            boolean last = request.getRequestId() == requests.get(requests.size() - 1).getRequestId();

            if ((time - prevTime) > 0){
                stats.add(prevTime * converter, contents);
                prevTime = time;
                contents.clear();
            }

            contents.add(requestContent);

            if (last){
                stats.add(prevTime * converter, contents);
            }
        }

        return stats.getDataList();
    }
}
