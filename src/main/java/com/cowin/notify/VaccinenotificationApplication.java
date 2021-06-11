package com.cowin.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cowin.notify.scheduler.Scheduler;

@SpringBootApplication
@EnableScheduling
public class VaccinenotificationApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VaccinenotificationApplication.class, args);

	}

}
