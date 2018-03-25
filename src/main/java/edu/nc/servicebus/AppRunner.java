package edu.nc.servicebus;

import edu.nc.servicebus.datagrid.dao.UserDao;
import edu.nc.servicebus.datagrid.model.User;
import edu.nc.servicebus.model.security.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppRunner implements ApplicationRunner{

    @Autowired
    private Registration registration;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if (registration.checkUsername("admin")){
            registration.addUser(new User("admin", "admin", new Date(), "admin@servicebus.com"));
        }
    }
}
