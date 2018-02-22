package edu.nc.servicebus.statistics.error;


import edu.nc.servicebus.statistics.StatsData;

public class ErrorData implements StatsData{

    private long time;
    private double errorCount;
    private double beachErrorCount;
    private double sportErrorCount;
    private double excursionErrorCount;

    public ErrorData(long time, double errorCount,
                     double beachErrorCount, double sportErrorCount, double excursionErrorCount){

        this.time = time;
        this.errorCount = errorCount;
        this.beachErrorCount = beachErrorCount;
        this.sportErrorCount = sportErrorCount;
        this.excursionErrorCount = excursionErrorCount;
    }

    public long getTime() {
        return time;
    }

    public double getErrorCount() {
        return errorCount;
    }

    public double getBeachErrorCount() {
        return beachErrorCount;
    }

    public double getSportErrorCount() {
        return sportErrorCount;
    }

    public double getExcursionErrorCount() {
        return excursionErrorCount;
    }
}
