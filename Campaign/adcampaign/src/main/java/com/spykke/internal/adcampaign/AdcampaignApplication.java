package com.spykke.internal.adcampaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spykke.internal"})
@EntityScan(basePackages = {"com.spykke.internal"})
@EnableJpaRepositories(basePackages = {"com.spykke.internal","com.spykke.internal.adcampaigndb.repository","com.spykke.internal.adcampaign.repository"})
@EnableAutoConfiguration
@EnableScheduling
public class AdcampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdcampaignApplication.class, args);
	}

}
