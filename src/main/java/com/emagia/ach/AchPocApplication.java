package com.emagia.ach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.emagia.ach.service",
		"com.emagia.ach.mapper", "com.emagia.ach.repository"})
@EnableScheduling
public class AchPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchPocApplication.class, args);
	}


	/*
	@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@ComponentScan(basePackages = {"es.unileon.inso2"})
@EntityScan("es.unileon.inso2.model")
@EnableJpaRepositories("es.unileon.inso2.repository")
	* */


}
