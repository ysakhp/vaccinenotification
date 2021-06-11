package com.cowin.notify.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cowin.notify.builder.RestRequestBuilder;
import com.cowin.notify.model.Center;
import com.cowin.notify.model.User;
import com.cowin.notify.service.EmailService;

@Component
@Scope("prototype")
public class Worker implements Runnable {

	private Logger log = LoggerFactory.getLogger(Worker.class);

	@Autowired
	RestRequestBuilder restRequestBuilder;

	@Autowired
	EmailService emailService;
	
	@Autowired
	WorkScheduler workScheduler;

	User user;

	void setUser(User user) {
		log.info("Setting User " + user.getEmail() + " " + user.getPincode());
		this.user = user;
	}

	@Override
	public void run() {
		log.info("Working " + user.getEmail());

		if (LocalDateTime.now().getHour() < 18) {

			String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(LocalDateTime.now())
					.toString();
			
			log.info("Today "+date+" Before 6 PM fetching vaccine details by pin no"+user.getPincode());

			restRequestBuilder.buildGetRequest(user.getPincode(), date).stream().forEach(center -> {
				log.info("going to stream sessions (Vaccine details filtering from session) for today"+user.getPincode()+" center +"+center.getName());
				center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0)
						.forEach(session -> {
							log.info("going to send mail");
							emailService.buildContent(user, center, session).notifyUser();
							workScheduler.removeUser(user);
						});

			});

		}

		
		String nextDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
				.format(LocalDateTime.now().plusDays(1)).toString();
		
		log.info(nextDate+" Fetching vaccine details by pin no : "+user.getPincode());

		restRequestBuilder.buildGetRequest(user.getPincode(), nextDate).stream().forEach(center -> {
			log.info("going to stream sessions (Vaccine details filtering from session) for next day "+user.getPincode()+" center +"+center.getName());

			center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0).forEach(session -> {
				log.info("going to send mail");
				emailService.buildContent(user, center, session).notifyUser();
				workScheduler.removeUser(user);
			});

		});
		
		

	}

}
