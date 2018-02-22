package edu.nc.servicebus.statistics;

import java.util.List;
import java.util.Map;

public interface Statistic {

    public void add(long time, Map<Integer, Content> contents);
    public List<StatsData> getDataList();
}
