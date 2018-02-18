package edu.nc.servicebus.statistics;


public class RequestData {

    private long time;
    private double requestCount;
    private double beachRequestCount;
    private double sportRequestCount;
    private double excursionRequestCount;

    public RequestData(){}

    public RequestData(long time, double requestCount, double beachRequestCount,
                       double sportRequestCount, double excursionRequestCount){
        this.time = time;
        this.requestCount = requestCount;
        this.beachRequestCount = beachRequestCount;
        this.sportRequestCount = sportRequestCount;
        this.excursionRequestCount = excursionRequestCount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(double requestCount) {
        this.requestCount = requestCount;
    }

    public double getBeachRequestCount() {
        return beachRequestCount;
    }

    public void setBeachRequestCount(double beachRequestCount) {
        this.beachRequestCount = beachRequestCount;
    }

    public double getSportRequestCount() {
        return sportRequestCount;
    }

    public void setSportRequestCount(double sportRequestCount) {
        this.sportRequestCount = sportRequestCount;
    }

    public double getExcursionRequestCount() {
        return excursionRequestCount;
    }

    public void setExcursionRequestCount(double excursionRequestCount) {
        this.excursionRequestCount = excursionRequestCount;
    }
}
