package com.cowin.notify.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cowin.notify.builder.RestRequestBuilder;
import com.cowin.notify.dao.UserDaoImpl;
import com.cowin.notify.model.Center;
import com.cowin.notify.model.CenterList;
import com.cowin.notify.service.EmailService;



@Service
public class Scheduler {
	
	

@Autowired
RestRequestBuilder restRequestBuilder;

	@Autowired
	private Logger log;
	
	public  Center model; 
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserDaoImpl userDaoImpl;

	public void req() {
		log.info("Scheduler");
		
		String urlOrg = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=690107&date=08-06-2021";
		
		String url= "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?";
		/*
		 * model = webClientBuilder.build() .get() .uri(
		 * "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=690107&date=08-06-2021")
		 * .retrieve() .bodyToMono(Center.class) .block();
		 */		
		
	
		
		
		String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(LocalDateTime.now().plusDays(1)).toString();
		
		userDaoImpl.getUsers().stream().forEach(user -> {
	
			restRequestBuilder.buildGetRequest(user.getPincode(),date).stream().forEach(center ->{
				center.getSessions().stream().filter(session -> session.getAvailable_capacity() >0).forEach(session ->{
					emailService.buildContent(user,center,session).notifyUser();
				});
				
			});
			
		
		});
		
		
		
		
	}

}
