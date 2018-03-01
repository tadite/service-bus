package edu.nc.servicebus.statistics.error;

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

@Component(value = "error")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ErrorStatistic implements Statistic{

    @Autowired
    private JsonCategories jsonCategories;

    private List<StatsData> errorDataList;

    {
        errorDataList = new ArrayList<>();
    }

    @Override
    public void add(long time, List<Content> contents) {
        double errorCount = contents.size();

        double beachErrorCount = 0;
        double sportErrorCount = 0;
        double excursionErrorCount = 0;

        for (Content content : contents){
            if (jsonCategories.checkEndpoint("beach", content.getEndpoint())){
                beachErrorCount++;
            }
            if (jsonCategories.checkEndpoint("sport", content.getEndpoint())){
                sportErrorCount++;
            }
            if (jsonCategories.checkEndpoint("excursion", content.getEndpoint())){
                excursionErrorCount++;
            }
        }

        errorDataList.add(new ErrorData(time, errorCount, beachErrorCount, sportErrorCount, excursionErrorCount));
    }

    @Override
    public List<StatsData> getDataList() {
        return this.errorDataList;
    }
}
