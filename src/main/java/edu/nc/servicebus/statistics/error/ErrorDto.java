package edu.nc.servicebus.statistics.error;


public class ErrorDto {

    private String time;
    private String name;
    private String url;
    private String description;

    public ErrorDto(){}

    public ErrorDto(String name, String time, String url, String description) {
        this.name = name;
        this.time = time;
        this.url = url;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
