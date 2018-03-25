package edu.nc.servicebus.statistics.error;


import edu.nc.servicebus.statistics.StatsData;

public class ErrorData implements StatsData{

    private long time;
    private double errorCount;
    private double hotelErrorCount;
    private double excursionsTripsterErrorCount;
    private double excursionsWeatlasErrorCount;
    private double ticketErrorCount;
    private double autoErrorCount;
    private double countryErrorCount;
    private double coastLivingErrorCount;

    public ErrorData(){}

    public ErrorData(long time, double errorCount, double hotelErrorCount,
                     double excursionsTripsterErrorCount, double excursionsWeatlasErrorCount,
                     double ticketErrorCount, double autoErrorCount, double countryErrorCount,
                     double coastLivingErrorCount) {

        this.time = time;
        this.errorCount = errorCount;
        this.hotelErrorCount = hotelErrorCount;
        this.excursionsTripsterErrorCount = excursionsTripsterErrorCount;
        this.excursionsWeatlasErrorCount = excursionsWeatlasErrorCount;
        this.ticketErrorCount = ticketErrorCount;
        this.autoErrorCount = autoErrorCount;
        this.countryErrorCount = countryErrorCount;
        this.coastLivingErrorCount = coastLivingErrorCount;
    }

    public long getTime() {
        return time;
    }

    public double getErrorCount() {
        return errorCount;
    }

    public double getHotelErrorCount() {
        return hotelErrorCount;
    }

    public double getExcursionsTripsterErrorCount() {
        return excursionsTripsterErrorCount;
    }

    public double getExcursionsWeatlasErrorCount() {
        return excursionsWeatlasErrorCount;
    }

    public double getTicketErrorCount() {
        return ticketErrorCount;
    }

    public double getAutoErrorCount() {
        return autoErrorCount;
    }

    public double getCountryErrorCount() {
        return countryErrorCount;
    }

    public double getCoastLivingErrorCount() {
        return coastLivingErrorCount;
    }
}
