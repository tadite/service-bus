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

        double hotelErrorCount = 0;
        double tripsterErrorCount = 0;
        double weatlasErrorCount = 0;
        double ticketErrorCount = 0;
        double autoErrorCount = 0;
        double countryErrorCount = 0;
        double costLivingErrorCount = 0;

        for (Content content : contents){
            if (jsonCategories.checkEndpoint("hotel", content.getEndpoint())){
                hotelErrorCount++;
            }
            if (jsonCategories.checkEndpoint("tripster", content.getEndpoint())){
                tripsterErrorCount++;
            }
            if (jsonCategories.checkEndpoint("weatlas", content.getEndpoint())){
                weatlasErrorCount++;
            }
            if (jsonCategories.checkEndpoint("ticket", content.getEndpoint())){
                ticketErrorCount++;
            }
            if (jsonCategories.checkEndpoint("auto", content.getEndpoint())){
                autoErrorCount++;
            }
            if (jsonCategories.checkEndpoint("country", content.getEndpoint())){
                countryErrorCount++;
            }
            if (jsonCategories.checkEndpoint("costOfLiving", content.getEndpoint())){
                costLivingErrorCount++;
            }
        }

        errorDataList.add(new ErrorData(time, errorCount,
                hotelErrorCount, tripsterErrorCount, weatlasErrorCount,
                ticketErrorCount, autoErrorCount, countryErrorCount,
                costLivingErrorCount));
    }

    @Override
    public List<StatsData> getDataList() {
        return this.errorDataList;
    }
}
