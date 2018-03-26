package edu.nc.servicebus.statistics.request;


import edu.nc.servicebus.statistics.StatsData;

public class RequestData implements StatsData {

    private long time;
    private double requestCount;
    private double hotelRequestCount;
    private double excursionTripsterRequestCount;
    private double excursionWeatlasRequestCount;
    private double ticketRequestCount;
    private double autoRequestCount;
    private double countryRequestCount;
    private double coastLivingRequestCount;
    private double avgTimeHotelRequest;
    private double avgTimeExcursionTripsterRequest;
    private double avgTimeExcursionWeatlasRequest;
    private double avgTimeTicketRequest;
    private double avgTimeAutoRequest;
    private double avgTimeCountryRequest;
    private double avgTimeCoastLivingRequest;

    public RequestData(){}

    public RequestData(long time, double requestCount, double hotelRequestCount,
                       double excursionTripsterRequestCount, double excursionWeatlasRequestCount,
                       double ticketRequestCount, double autoRequestCount, double countryRequestCount,
                       double coastLivingRequestCount, double avgTimeHotelRequest,
                       double avgTimeExcursionTripsterRequest, double avgTimeExcursionWeatlasRequest,
                       double avgTimeTicketRequest, double avgTimeAutoRequest, double avgTimeCountryRequest,
                       double avgTimeCoastLivingRequest) {

        this.time = time;
        this.requestCount = requestCount;
        this.hotelRequestCount = hotelRequestCount;
        this.excursionTripsterRequestCount = excursionTripsterRequestCount;
        this.excursionWeatlasRequestCount = excursionWeatlasRequestCount;
        this.ticketRequestCount = ticketRequestCount;
        this.autoRequestCount = autoRequestCount;
        this.countryRequestCount = countryRequestCount;
        this.coastLivingRequestCount = coastLivingRequestCount;
        this.avgTimeHotelRequest = avgTimeHotelRequest;
        this.avgTimeExcursionTripsterRequest = avgTimeExcursionTripsterRequest;
        this.avgTimeExcursionWeatlasRequest = avgTimeExcursionWeatlasRequest;
        this.avgTimeTicketRequest = avgTimeTicketRequest;
        this.avgTimeAutoRequest = avgTimeAutoRequest;
        this.avgTimeCountryRequest = avgTimeCountryRequest;
        this.avgTimeCoastLivingRequest = avgTimeCoastLivingRequest;
    }

    public long getTime() {
        return time;
    }

    public double getRequestCount() {
        return requestCount;
    }

    public double getHotelRequestCount() {
        return hotelRequestCount;
    }

    public double getExcursionTripsterRequestCount() {
        return excursionTripsterRequestCount;
    }

    public double getExcursionWeatlasRequestCount() {
        return excursionWeatlasRequestCount;
    }

    public double getTicketRequestCount() {
        return ticketRequestCount;
    }

    public double getAutoRequestCount() {
        return autoRequestCount;
    }

    public double getCountryRequestCount() {
        return countryRequestCount;
    }

    public double getCoastLivingRequestCount() {
        return coastLivingRequestCount;
    }

    public double getAvgTimeHotelRequest() {
        return avgTimeHotelRequest;
    }

    public double getAvgTimeExcursionTripsterRequest() {
        return avgTimeExcursionTripsterRequest;
    }

    public double getAvgTimeExcursionWeatlasRequest() {
        return avgTimeExcursionWeatlasRequest;
    }

    public double getAvgTimeTicketRequest() {
        return avgTimeTicketRequest;
    }

    public double getAvgTimeAutoRequest() {
        return avgTimeAutoRequest;
    }

    public double getAvgTimeCountryRequest() {
        return avgTimeCountryRequest;
    }

    public double getAvgTimeCoastLivingRequest() {
        return avgTimeCoastLivingRequest;
    }
}
