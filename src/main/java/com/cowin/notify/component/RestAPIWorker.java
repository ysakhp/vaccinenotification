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
import com.cowin.notify.service.UserService;
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

	@Autowired
	UserService userService;
	
	private boolean isEmailSentForUser = false;

	@Override
	public synchronized void run() {

		log.info("Rest API Worker thread start.");
		synchronized (users) {
			users.stream().forEach(user -> {
				log.info("User :" + user);
				getCowinDetails(user);

			});
		}

		log.info("Rest API Worker thread ends.");

	}

	public void setUsers(Vector<User> users) {
		log.info("Users set to Call External API");
		this.users = users;

	}

	public void getCowinDetails(User user) {

		synchronized (user) {

			log.info("Getting cowin details  for " + user.getEmail() + " ID : " + user.getId());

			String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(LocalDateTime.now())
					.toString();

			try {
				cowinRequestBuilder.getCowinDetails(user.getPincode(), date).stream().forEach(center -> {
					log.info("Fetched cowin details going to check vaccine available  " + user.getPincode()
							+ " center +" + center.getName());
					center.getSessions().stream()
							.filter(session -> (session.getAvailable_capacity() > 0 && session.getMin_age_limit() == user.getAgeGroup())&& (user.getEmailSendDate() == null
									|| user.getEmailSendDate().equals(date) || user.getEmailSendDate().isEmpty()))
							.forEach(session -> {

								log.info("Going to send mail :" + user.getEmail() + " " + center.getName() + " on "
										+ session.getDate());
								emailService.buildContent(user, center, session).notifyUser();
								log.error("ID " + user.getId() + " Email Sent " + user.getEmail() + " Pin : "
										+ user.getPincode() + "Place : " + center.getName());

								user.setEmailSent(true);
								isEmailSentForUser = true;

							});

				});

				if (isEmailSentForUser) {

					log.info("Email sent count increase " + user.getEmailCount());
					user.setEmailCount(user.getEmailCount() + 1);

					if (user.getEmailCount() == 20) {
						String nextDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
								.format(LocalDateTime.now().plusDays(1)).toString();
						/*if (!user.getEmail().equals("ysakhpr@gmail.com")) {
							log.info("Omit ysakh");
							user.setEmailSendDate(nextDate);
						}*/
						user.setEmailSendDate(nextDate);
						user.setEmailCount(0);
						user.setEmailSent(false);

						log.info("User email sending details setting default");
						log.info("Removed User " + user.getEmail());
//						users.remove(user);

					}
					log.info("Updating user after sending mail");
					userService.updateUser(user);
					isEmailSentForUser = false;
				}

			} catch (IOException | InterruptedException e) {
				log.info("Exception occured " + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
