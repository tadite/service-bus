package edu.nc.servicebus.model.limiter;

public interface RateLimiterManager {
    void createRateLimiterIfAbsent(String name, Double permitsPerSecond);
    boolean tryAcquire(String name);
}
