package edu.nc.servicebus.statistics.request;


import edu.nc.servicebus.statistics.StatsData;

public class RequestData implements StatsData {

    private long time;
    private double requestCount;
    private double beachRequestCount;
    private double sportRequestCount;
    private double excursionRequestCount;
    private double avgTimeBeachRequest;
    private double avgTimeSportRequest;
    private double avgTimeExcursionRequest;

    public RequestData(){}

    public RequestData(long time, double requestCount, double beachRequestCount,
                       double sportRequestCount, double excursionRequestCount,
                       double avgTimeBeachRequest, double avgTimeSportRequest,
                       double avgTimeExcursionRequest){

        this.time = time;
        this.requestCount = requestCount;
        this.beachRequestCount = beachRequestCount;
        this.sportRequestCount = sportRequestCount;
        this.excursionRequestCount = excursionRequestCount;
        this.avgTimeBeachRequest = avgTimeBeachRequest;
        this.avgTimeSportRequest = avgTimeSportRequest;
        this.avgTimeExcursionRequest = avgTimeExcursionRequest;
    }

    public long getTime() {
        return time;
    }

    public double getRequestCount() {
        return requestCount;
    }

    public double getBeachRequestCount() {
        return beachRequestCount;
    }

    public double getSportRequestCount() {
        return sportRequestCount;
    }

    public double getExcursionRequestCount() {
        return excursionRequestCount;
    }

    public double getAvgTimeBeachRequest() {
        return avgTimeBeachRequest;
    }

    public double getAvgTimeSportRequest() {
        return avgTimeSportRequest;
    }

    public double getAvgTimeExcursionRequest() {
        return avgTimeExcursionRequest;
    }
}
