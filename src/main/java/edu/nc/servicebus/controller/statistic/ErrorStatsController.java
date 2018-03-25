package edu.nc.servicebus.controller.statistic;

import edu.nc.servicebus.datagrid.dao.ErrorDao;
import edu.nc.servicebus.datagrid.dao.LogDao;
import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.model.Error;
import edu.nc.servicebus.datagrid.model.Request;
import edu.nc.servicebus.statistics.Content;
import edu.nc.servicebus.statistics.Statistic;
import edu.nc.servicebus.statistics.StatsData;
import edu.nc.servicebus.statistics.error.ErrorContent;
import edu.nc.servicebus.statistics.error.ErrorStatistic;
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
public class ErrorStatsController {

    @Autowired
    private LogDao logDao;

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private ErrorDao errorDao;

    @Autowired
    @Qualifier("error")
    private ObjectFactory<ErrorStatistic> errorStatistic;

    @RequestMapping("/errorPerDay")
    public ResponseEntity errorPerDay(){
        return ResponseEntity.ok(getErrorDataList(24, 60));
    }

    @RequestMapping("/errorPerHour")
    public ResponseEntity errorPerHour(){
        return ResponseEntity.ok(getErrorDataList(1, 60));
    }

    @RequestMapping("/errorPerMinute")
    public ResponseEntity errorPerMinute(){
        return ResponseEntity.ok(getErrorDataList(1, 1));
    }

    private List<StatsData> getErrorDataList(int hour, int minute){
        List<Error> errors = errorDao.getErrorList();
        List<Content> contents = new ArrayList<>();

        Statistic stats = errorStatistic.getObject();

        long converter = 1000 * hour * minute * 60;
        long prevTime = errors.get(0).getTime().getTime() / converter;
        long time = 0;

        for (Error error : errors){
            time = error.getTime().getTime() / converter;

            Request req = null;
            try {
                req = requestDao.findById(logDao.findByErrorId(error.getErrorId()).getRequestId());
            } catch (NullPointerException e){
                continue;
            }

            Content errorContent = new ErrorContent(req.getContent(), error.getReason());

            boolean last = error.getErrorId() == errors.get(errors.size() - 1).getErrorId();

            if ((time - prevTime) > 0){
                stats.add(prevTime * converter, contents);
                prevTime = time;
                contents.clear();
            }

            contents.add(errorContent);

            if (last){
                stats.add(prevTime * converter, contents);
            }
        }

        return stats.getDataList();
    }
}
