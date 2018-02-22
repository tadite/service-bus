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

    @RequestMapping("/errorStats")
    public ResponseEntity errorStats(){
        return ResponseEntity.ok(getErrorDataList());
    }

    private List<StatsData> getErrorDataList(){
        List<Error> errors = errorDao.getErrorList();
        List<Content> contents = new ArrayList<>();

        Statistic stats = errorStatistic.getObject();

        long converter = 1000 * 24 * 60 * 60;
        long prevTime = errors.get(0).getTime().getTime() / converter;
        long time = 0;

        for (Error error : errors){
            time = error.getTime().getTime() / converter;

            Request req = requestDao.findById(logDao.findByErrorId(error.getErrorId()).getRequestId());
            Content errorContent = new ErrorContent(req.getContent(), error.getReason());

            boolean last = error.getErrorId() == errors.get(errors.size() - 1).getErrorId();

            if ((time - prevTime) > 0 || last){
                if (last){
                    contents.add(errorContent);
                }
                stats.add(prevTime * converter, contents);
                prevTime = time;
                contents.clear();
                contents.add(errorContent);
            }

            contents.add(errorContent);
        }

        return stats.getDataList();
    }
}
