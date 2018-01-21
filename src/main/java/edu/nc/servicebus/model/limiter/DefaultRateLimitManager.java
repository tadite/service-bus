package edu.nc.servicebus.model.limiter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class DefaultRateLimitManager implements RateLimiterManager {

    private final long TIMEOUT_SECONDS = 5;
    private Map<String, RateLimiter> rateLimiterMap = new HashMap<>();

    @Override
    public void createRateLimiterIfAbsent(String name, Double permitsPerSecond) {
        if (rateLimiterMap.containsKey(name))
            return;

        RateLimiter newRateLimiter = RateLimiter.create(permitsPerSecond);

        rateLimiterMap.put(name, newRateLimiter);
    }

    @Override
    public boolean tryAcquire(String name) {
        return rateLimiterMap.get(name).tryAcquire(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
}