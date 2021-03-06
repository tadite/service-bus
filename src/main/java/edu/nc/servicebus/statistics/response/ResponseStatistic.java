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
        int hotelCount = 0;
        int tripsterCount = 0;
        int weatlasCount = 0;
        int ticketCount = 0;
        int autoCount = 0;
        int countryCount = 0;
        int costLivingCount = 0;

        double hotelTime = 0;
        double tripsterTime = 0;
        double weatlasTime = 0;
        double ticketTime = 0;
        double autoTime = 0;
        double countryTime = 0;
        double costLivingTime = 0;

        double hotelSize = 0;
        double tripsterSize = 0;
        double weatlasSize = 0;
        double ticketSize = 0;
        double autoSize = 0;
        double countrySize = 0;
        double costLivingSize = 0;

        for (Content content : contents){
            if (jsonCategories.checkEndpoint("hotel", content.getEndpoint())){
                hotelCount++;
                hotelCount += content.getTime();
                hotelSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("tripster", content.getEndpoint())){
                tripsterCount++;
                tripsterTime += content.getTime();
                tripsterSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("weatlas", content.getEndpoint())){
                weatlasCount++;
                weatlasTime += content.getTime();
                weatlasSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("ticket", content.getEndpoint())){
                ticketCount++;
                ticketTime += content.getTime();
                ticketSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("auto", content.getEndpoint())){
                autoCount++;
                autoTime += content.getTime();
                autoSize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("country", content.getEndpoint())){
                countryCount++;
                countryTime += content.getTime();
                countrySize += content.getContent().length();
            }
            if (jsonCategories.checkEndpoint("costOfLiving", content.getEndpoint())){
                costLivingCount++;
                costLivingTime += content.getTime();
                costLivingSize += content.getContent().length();
            }
        }

        responseDataList.add(new ResponseData(time, hotelTime / hotelCount,
                tripsterTime / tripsterCount,
                weatlasTime / weatlasCount,
                ticketTime / ticketCount,
                autoTime / autoCount,
                countryTime / countryCount,
                costLivingTime / costLivingCount,
                hotelSize / hotelCount,
                tripsterSize / tripsterCount,
                weatlasSize / weatlasCount,
                ticketSize / ticketCount,
                autoSize / autoCount,
                countrySize / countryCount,
                costLivingSize / costLivingCount));
    }

    @Override
    public List<StatsData> getDataList() {
        return this.responseDataList;
    }
}
