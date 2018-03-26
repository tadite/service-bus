package edu.nc.servicebus.controller.statistic;

import edu.nc.servicebus.datagrid.dao.LogDao;
import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.dao.ResponseDao;
import edu.nc.servicebus.datagrid.model.Request;
import edu.nc.servicebus.datagrid.model.Response;
import edu.nc.servicebus.statistics.Content;
import edu.nc.servicebus.statistics.Statistic;
import edu.nc.servicebus.statistics.StatsData;
import edu.nc.servicebus.statistics.response.ResponseContent;
import edu.nc.servicebus.statistics.response.ResponseStatistic;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class ResponseStatsController {

    @Autowired
    private LogDao logDao;

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    @Qualifier("response")
    private ObjectFactory<ResponseStatistic> responseStatistic;

    @RequestMapping("/responsePerDay")
    public ResponseEntity responsePerDay(){
        return ResponseEntity.ok(getResponseDataList(24, 60));
    }

    @RequestMapping("/responsePerHour")
    public ResponseEntity responsePerHour(){
        return ResponseEntity.ok(getResponseDataList(1, 60));
    }

    @RequestMapping("/responsePerMinute")
    public ResponseEntity responsePerMinute(){
        return ResponseEntity.ok(getResponseDataList(1, 1));
    }

    private List<StatsData> getResponseDataList(int hour, int minute){
        List<Response> responses = responseDao.getResponseList();
        List<Content> contents = new ArrayList<>();

        Statistic stats = responseStatistic.getObject();

        long converter = 1000 * hour * minute * 60;
        long prevTime  = responses.get(0).getTime().getTime() / converter;
        long time = 0;

        for (Response response : responses){
            time = response.getTime().getTime() / converter;

            Request req = null;
            try {
                req = requestDao.findById(logDao.findByResponseId(response.getResponseId()).getRequestId());
            } catch (NullPointerException e){
                continue;
            }

            int responseTime = (int) (response.getEndTime().getTime() - response.getTime().getTime());
            Content responseContent = new ResponseContent(responseTime, req.getContent(), response.getContent());

            boolean last = response.getResponseId() == responses.get(responses.size() - 1).getResponseId();

            if ((time - prevTime) > 0){
                stats.add(prevTime * converter, contents);
                prevTime = time;
                contents.clear();
            }

            contents.add(responseContent);

            if (last){
                stats.add(prevTime * converter, contents);
            }
        }

        return stats.getDataList();
    }
}
