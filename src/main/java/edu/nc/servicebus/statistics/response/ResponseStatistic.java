package edu.nc.servicebus.statistics.response;

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

@Component(value = "response")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ResponseStatistic implements Statistic{

    @Autowired
    private JsonCategories jsonCategories;

    private List<StatsData> responseDataList;

    {
        responseDataList = new ArrayList<>();
    }

    @Override
    public void add(long time, List<Content> contents) {
        int beachCount = 0;
        int sportCount = 0;
        int excursionCount = 0;

        double beachTime = 0;
        double sportTime = 0;
        double excursionTime = 0;

        double beachSize = 0;
        double sportSize = 0;
        double excursionSize = 0;

        for (Content content : contents){
            if (jsonCategories.checkEndpoint("beach", content.getEndpoint())){
                beachCount++;
                beachTime += content.getTime() / 1000;
                beachSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("sport", content.getEndpoint())){
                sportCount++;
                sportTime += content.getTime() / 1000;
                sportSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("excursion", content.getEndpoint())){
                excursionCount++;
                excursionTime += content.getTime() / 1000;
                excursionSize += content.getContent().length();
            }
        }

        responseDataList.add(new ResponseData(time,
                beachTime / beachCount,
                sportTime / sportCount,
                excursionTime / excursionCount,
                beachSize / beachCount,
                sportSize / sportCount,
                excursionSize / excursionCount));
    }

    @Override
    public List<StatsData> getDataList() {
        return this.responseDataList;
    }
}
