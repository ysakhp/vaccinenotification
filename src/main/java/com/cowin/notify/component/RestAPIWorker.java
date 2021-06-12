package com.cowin.notify.component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cowin.notify.builder.CowinRequestBuilder;
import com.cowin.notify.builder.RestRequestBuilder;
import com.cowin.notify.model.User;
import com.cowin.notify.service.EmailService;
import com.sun.jdi.connect.Connector.BooleanArgument;

@Component
@Scope("prototype")
public class RestAPIWorker implements Runnable {

	private Logger log = LoggerFactory.getLogger(RestAPIWorker.class);
	private volatile Vector<User> users;

	@Autowired
	private RestRequestBuilder restRequestBuilder;
	
	@Autowired
	private CowinRequestBuilder cowinRequestBuilder;

	@Autowired
	EmailService emailService;

	@Override
	public synchronized void run() {

		log.info("Rest API Worker thread start.");

		users.parallelStream().forEach(user -> {
			getCowinDetails(user);

		});

		log.info("Rest API Worker thread ends.");

	}

	public void setUsers(Vector<User> users) {
		log.info("Users set to Call External API");
		this.users = users;

	}

	public void getCowinDetails(User user) {

		synchronized (user) {

			log.info("Getting cowin details  for " + user);

			String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(LocalDateTime.now())
					.toString();

			try {
				cowinRequestBuilder.getCowinDetails(user.getPincode(), date).getCenters().stream().forEach(center -> {
					log.info("Fetched cowin details going to check vaccine available  "
							+ user.getPincode() + " center +" + center.getName());
					center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0)
							.forEach(session -> {

								log.info("Filtered Session by avaliable capacity for center " + center.getName() + " on "
										+ session.getDate());

								log.info("Going to send mail");

								emailService.buildContent(user, center, session).notifyUser();
								log.info("Email Sent" + user.getEmail());
								user.setEmailSent(true);

							});

				});
				
				
				if (user.isEmailSent()) {
					log.info("Removed User " + user);
					users.remove(user);
				}
			} catch (IOException | InterruptedException e) {
				log.info("Exception occured "+e.getMessage());
				e.printStackTrace();
			}
		}

		
	}

}
