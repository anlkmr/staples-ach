package com.emagia.ach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AchPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchPocApplication.class, args);
	}

}
