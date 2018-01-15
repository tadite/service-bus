package edu.nc.servicebus.model.action;

import edu.nc.servicebus.model.limiter.RateLimiterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultActionFactory implements ActionFactory {

    @Autowired
    RateLimiterManager rateLimiterManager;

    @Autowired
    ActionReader actionReader;

    @Override
    public Action getAction(String name) {
        Action action = createByName(name);

        rateLimiterManager.createRateLimiterIfAbsent(name, action.getRate());

        return action;
    }

    private Action createByName(String name) {
        return actionReader.createByName(name);
    }

    @Override
    public Collection<String> getActionNames() {
        return actionReader.getAvailableActionNames();
    }
}
