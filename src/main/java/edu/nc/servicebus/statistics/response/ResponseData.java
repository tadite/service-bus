package edu.nc.servicebus.statistics.response;


import edu.nc.servicebus.statistics.StatsData;

public class ResponseData implements StatsData {

    private long time;
    private double avgTimeBeachResponse;
    private double avgTimeSportResponse;
    private double avgTimeExcursionResponse;
    private double avgSizeBeachResponse;
    private double avgSizeSportResponse;
    private double avgSizeExcursionResponse;

    public ResponseData(long time, double avgTimeBeachResponse,
                        double avgTimeSportResponse, double avgTimeExcursionResponse,
                        double avgSizeBeachResponse, double avgSizeSportResponse,
                        double avgSizeExcursionResponse){

        this.time = time;
        this.avgTimeBeachResponse = avgTimeBeachResponse;
        this.avgTimeSportResponse = avgTimeSportResponse;
        this.avgTimeExcursionResponse = avgTimeExcursionResponse;
        this.avgSizeBeachResponse = avgSizeBeachResponse;
        this.avgSizeSportResponse = avgSizeSportResponse;
        this.avgSizeExcursionResponse = avgSizeExcursionResponse;
    }

    public long getTime() {
        return time;
    }

    public double getAvgTimeBeachResponse() {
        return avgTimeBeachResponse;
    }

    public double getAvgTimeSportResponse() {
        return avgTimeSportResponse;
    }

    public double getAvgTimeExcursionResponse() {
        return avgTimeExcursionResponse;
    }

    public double getAvgSizeBeachResponse() {
        return avgSizeBeachResponse;
    }

    public double getAvgSizeSportResponse() {
        return avgSizeSportResponse;
    }

    public double getAvgSizeExcursionResponse() {
        return avgSizeExcursionResponse;
    }
}
