package edu.nc.servicebus.statistics.request;

import edu.nc.servicebus.statistics.Content;
import edu.nc.servicebus.statistics.Statistic;
import edu.nc.servicebus.statistics.StatsData;
import edu.nc.servicebus.statistics.categories.JsonCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(value = "request")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestStatistic implements Statistic {

    @Autowired
    private JsonCategories jsonCategories;

    private List<StatsData> requestDataList;


    {
        requestDataList = new ArrayList<>();
    }

    @Override
    public void add(long time, Map<Integer, Content> contents){
        int beachCount = 0;
        int sportCount = 0;
        int excursionCount = 0;

        double beachTime = 0;
        double sportTime = 0;
        double excursionTime = 0;

        for (Map.Entry<Integer, Content> content : contents.entrySet()){
            if (jsonCategories.checkEndpoint("beach", content.getValue().getEndpoint())){
                beachCount++;
                beachTime += content.getKey() / 1000;
            }
            if (jsonCategories.checkEndpoint("sport", content.getValue().getEndpoint())){
                sportCount++;
                sportTime += content.getKey() / 1000;
            }
            if (jsonCategories.checkEndpoint("excursion", content.getValue().getEndpoint())){
                excursionCount++;
                excursionTime += content.getKey() / 1000;
            }
        }
        requestDataList.add(new RequestData(time, contents.size(),
                beachCount, sportCount, excursionCount,
                beachTime / beachCount,
                sportTime / sportCount,
                excursionTime / excursionCount));
    }

    @Override
    public List<StatsData> getDataList(){
        return this.requestDataList;
    }
}
