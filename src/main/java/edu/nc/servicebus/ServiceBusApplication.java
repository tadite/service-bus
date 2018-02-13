package edu.nc.servicebus;

import edu.nc.servicebus.controller.EndpointController;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServiceBusApplication {

	@Bean
	public Ignite ignite(){

		IgniteConfiguration cfg = new IgniteConfiguration();

// Ignite persistence configuration.
		DataStorageConfiguration storageCfg = new DataStorageConfiguration();

// Enabling the persistence.
		storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);

// Applying settings.
		cfg.setDataStorageConfiguration(storageCfg);
		Ignite ignite = Ignition.start(cfg);
		ignite.active(true);
		return ignite;

	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceBusApplication.class, args);
	}
}
