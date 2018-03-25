package edu.nc.servicebus.statistics.response;


import edu.nc.servicebus.statistics.StatsData;

public class ResponseData implements StatsData {

    private long time;
    private double avgTimeHotelResponse;
    private double avgTimeExcursionsTripsterResponse;
    private double avgTimeExcursionsWeatlasResponse;
    private double avgTimeTicketResponse;
    private double avgTimeAutoResponse;
    private double avgTimCountryResponse;
    private double avgTimeCoastLivingResponse;
    private double avgSizeHotelResponse;
    private double avgSizeExcursionsTripsterResponse;
    private double avgSizeExcursionsWeatlasResponse;
    private double avgSizeTicketResponse;
    private double avgSizeAutoResponse;
    private double avgSizeCountryResponse;
    private double avgSizeCoastLivingResponse;

    public ResponseData(){}

    public ResponseData(long time, double avgTimeHotelResponse, double avgTimeExcursionsTripsterResponse,
                        double avgTimeExcursionsWeatlasResponse, double avgTimeTicketResponse,
                        double avgTimeAutoResponse, double avgTimCountryResponse, double avgTimeCoastLivingResponse,
                        double avgSizeHotelResponse, double avgSizeExcursionsTripsterResponse,
                        double avgSizeExcursionsWeatlasResponse, double avgSizeTicketResponse,
                        double avgSizeAutoResponse, double avgSizeCountryResponse,
                        double avgSizeCoastLivingResponse) {

        this.time = time;
        this.avgTimeHotelResponse = avgTimeHotelResponse;
        this.avgTimeExcursionsTripsterResponse = avgTimeExcursionsTripsterResponse;
        this.avgTimeExcursionsWeatlasResponse = avgTimeExcursionsWeatlasResponse;
        this.avgTimeTicketResponse = avgTimeTicketResponse;
        this.avgTimeAutoResponse = avgTimeAutoResponse;
        this.avgTimCountryResponse = avgTimCountryResponse;
        this.avgTimeCoastLivingResponse = avgTimeCoastLivingResponse;
        this.avgSizeHotelResponse = avgSizeHotelResponse;
        this.avgSizeExcursionsTripsterResponse = avgSizeExcursionsTripsterResponse;
        this.avgSizeExcursionsWeatlasResponse = avgSizeExcursionsWeatlasResponse;
        this.avgSizeTicketResponse = avgSizeTicketResponse;
        this.avgSizeAutoResponse = avgSizeAutoResponse;
        this.avgSizeCountryResponse = avgSizeCountryResponse;
        this.avgSizeCoastLivingResponse = avgSizeCoastLivingResponse;
    }

    public long getTime() {
        return time;
    }

    public double getAvgTimeHotelResponse() {
        return avgTimeHotelResponse;
    }

    public double getAvgTimeExcursionsTripsterResponse() {
        return avgTimeExcursionsTripsterResponse;
    }

    public double getAvgTimeExcursionsWeatlasResponse() {
        return avgTimeExcursionsWeatlasResponse;
    }

    public double getAvgTimeTicketResponse() {
        return avgTimeTicketResponse;
    }

    public double getAvgTimeAutoResponse() {
        return avgTimeAutoResponse;
    }

    public double getAvgTimCountryResponse() {
        return avgTimCountryResponse;
    }

    public double getAvgTimeCoastLivingResponse() {
        return avgTimeCoastLivingResponse;
    }

    public double getAvgSizeHotelResponse() {
        return avgSizeHotelResponse;
    }

    public double getAvgSizeExcursionsTripsterResponse() {
        return avgSizeExcursionsTripsterResponse;
    }

    public double getAvgSizeExcursionsWeatlasResponse() {
        return avgSizeExcursionsWeatlasResponse;
    }

    public double getAvgSizeTicketResponse() {
        return avgSizeTicketResponse;
    }

    public double getAvgSizeAutoResponse() {
        return avgSizeAutoResponse;
    }

    public double getAvgSizeCountryResponse() {
        return avgSizeCountryResponse;
    }

    public double getAvgSizeCoastLivingResponse() {
        return avgSizeCoastLivingResponse;
    }
}
