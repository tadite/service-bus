package edu.nc.servicebus;

import edu.nc.servicebus.controller.EndpointController;
import edu.nc.servicebus.model.action.MockActionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServiceBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBusApplication.class, args);
	}
}
