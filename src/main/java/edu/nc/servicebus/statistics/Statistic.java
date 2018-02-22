package edu.nc.servicebus.statistics;

import java.util.List;

public interface Statistic {

    public void add(long time, List<Content> contents);
    public List<StatsData> getDataList();
}
