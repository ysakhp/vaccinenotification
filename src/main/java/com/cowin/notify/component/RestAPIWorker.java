package com.cowin.notify.component;

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

import com.cowin.notify.builder.RestRequestBuilder;
import com.cowin.notify.model.User;
import com.cowin.notify.service.EmailService;
import com.sun.jdi.connect.Connector.BooleanArgument;

@Component
@Scope("prototype")
public class RestAPIWorker implements Callable {

	private Logger log = LoggerFactory.getLogger(RestAPIWorker.class);
	private volatile Vector<User> users;

	boolean isEmailSent = false;

	@Autowired
	private RestRequestBuilder restRequestBuilder;

	@Autowired
	EmailService emailService;

	@Override
	public synchronized Boolean call() {

		users.stream().forEach(user -> {

			log.info("RestApiWorker for User " + user);

			String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(LocalDateTime.now())
					.toString();

			restRequestBuilder.buildGetRequest(user.getPincode(), date).stream().forEach(center -> {
				log.info("Test to stream sessions (Vaccine details filtering from session) for any day"
						+ user.getPincode() + " center +" + center.getName());
				center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0)
						.forEach(session -> {

							log.info("Filtered Session by avaliable capacity for center " + center.getName() + " on "
									+ session.getDate());
							
								log.info("going to send mail");

								isEmailSent = emailService.buildContent(user, center, session).notifyUser();
								log.info("Email Sent");
								user.setEmailSent(true);
								

							
						});
				
				if(user.isEmailSent()) {
					log.info("Remove User" + user);
					users.remove(user);
				}
				
				

			});

			/*
			 * if (LocalDateTime.now().getHour() < 18) {
			 * 
			 * date = DateTimeFormatter.ofPattern("dd-MM-yyyy",
			 * Locale.ENGLISH).format(LocalDateTime.now()) .toString();
			 * 
			 * log.info("Today " + date + " Before 6 PM fetching vaccine details by pin no"
			 * + user.getPincode());
			 * 
			 * restRequestBuilder.buildGetRequest(user.getPincode(),
			 * date).stream().forEach(center -> { log.
			 * info("going to stream sessions (Vaccine details filtering from session) for today"
			 * + user.getPincode() + " center +" + center.getName());
			 * center.getSessions().stream().filter(session ->
			 * session.getAvailable_capacity() > 0) .forEach(session -> { if
			 * (!user.isEmailSent()) { log.info("going to send mail"); isEmailSent =
			 * emailService.buildContent(user, center, session).notifyUser();
			 * log.info("Remove User" + user); user.setEmailSent(true); users.remove(user);
			 * } });
			 * 
			 * });
			 * 
			 * }
			 * 
			 * String nextDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
			 * .format(LocalDateTime.now().plusDays(1)).toString();
			 * 
			 * log.info(nextDate + " Fetching vaccine details by pin no : " +
			 * user.getPincode());
			 * 
			 * restRequestBuilder.buildGetRequest(user.getPincode(),
			 * nextDate).stream().forEach(center -> { log.
			 * info("going to stream sessions (Vaccine details filtering from session) for next day "
			 * + user.getPincode() + " center +" + center.getName());
			 * 
			 * center.getSessions().stream().filter(session ->
			 * session.getAvailable_capacity() > 0) .forEach(session -> { if
			 * (!user.isEmailSent()) { log.info("going to send mail"); isEmailSent =
			 * emailService.buildContent(user, center, session).notifyUser();
			 * log.info("Remove User" + user); user.setEmailSent(true); users.remove(user);
			 * } });
			 * 
			 * });
			 */

		});

		return Boolean.valueOf(isEmailSent);

	}

	public void setUsers(Vector<User> users) {
		log.info("Users set");
		this.users = users;

	}

	/*
	 * @Override public void run() { log.info("Thread is running " +
	 * Thread.currentThread().getName());
	 * 
	 * this.users.stream().forEach(user -> {
	 * 
	 * log.info("RestApiWorker " + user);
	 * 
	 * if (LocalDateTime.now().getHour() < 18) {
	 * 
	 * String date = DateTimeFormatter.ofPattern("dd-MM-yyyy",
	 * Locale.ENGLISH).format(LocalDateTime.now()) .toString();
	 * 
	 * log.info("Today " + date + " Before 6 PM fetching vaccine details by pin no"
	 * + user.getPincode());
	 * 
	 * restRequestBuilder.buildGetRequest(user.getPincode(),
	 * date).stream().forEach(center -> { log.
	 * info("going to stream sessions (Vaccine details filtering from session) for today"
	 * + user.getPincode() + " center " + center.getName());
	 * center.getSessions().stream().filter(session ->
	 * session.getAvailable_capacity() > 0) .forEach(session -> {
	 * log.info("going to send mail"); isEmailSent = emailService.buildContent(user,
	 * center, session).notifyUser();
	 * 
	 * log.info("Remove User" + user); log.info(users.toString()); });
	 * 
	 * });
	 * 
	 * }
	 * 
	 * String nextDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
	 * .format(LocalDateTime.now().plusDays(1)).toString();
	 * 
	 * log.info(nextDate + " Fetching vaccine details by pin no : " +
	 * user.getPincode());
	 * 
	 * restRequestBuilder.buildGetRequest(user.getPincode(),
	 * nextDate).stream().forEach(center -> { log.
	 * info("going to stream sessions (Vaccine details filtering from session) for next day "
	 * + user.getPincode() + " center +" + center.getName());
	 * 
	 * center.getSessions().stream().filter(session ->
	 * session.getAvailable_capacity() > 0) .forEach(session -> {
	 * log.info("going to send mail"); isEmailSent = emailService.buildContent(user,
	 * center, session).notifyUser();
	 * 
	 * log.info("Remove User" + user); log.info(users.toString());
	 * 
	 * });
	 * 
	 * });
	 * 
	 * });
	 * 
	 * 
	 * 
	 * }
	 */

}
