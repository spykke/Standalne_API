package com.spykke.subscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spykke.subscribe"})
@EntityScan(basePackages = {"com.spykke.subscribe"})
@EnableJpaRepositories(basePackages = {"com.spykke.subscribe"})
@EnableAutoConfiguration
public class SubscribeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscribeApplication.class, args);
	}

}
