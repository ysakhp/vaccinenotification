package com.cowin.notify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cowin.notify.model.Center;
import com.cowin.notify.model.Session;
import com.cowin.notify.model.User;

@Service
public class EmailService {

	Logger log = LoggerFactory.getLogger(EmailService.class);
	@Autowired
	private JavaMailSender emailSender;

	private String toEmail;
	private String content;
	private String subject;

	public void sendSimpleMessage(String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@vaccinenotification.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);

	}

	public Boolean notifyUser() {
		Boolean isEmailSent = false;
		try {
			sendSimpleMessage(toEmail, subject, content);
			isEmailSent = true;
		} catch (Exception e) {
			isEmailSent = false;
			log.error("Failed Sending mail "+e.getMessage());
		}
		return isEmailSent;
	}

	public EmailService buildContent(User user, Center center, Session session) {
		content = "Hai,\nVaccine is available at " + center.getName() +" ( "+user.getPincode()+" )"
				+ " on "+session.getDate()+". Immediately schedule the timing by logging in https://selfregistration.cowin.gov.in/ \nAvailable : "
				+ session.getAvailable_capacity() + "\nDose 1: " + session.getAvailable_capacity_dose1() + "\nDose 2 :"
				+ session.getAvailable_capacity_dose2()+"\n\nRegards,\nVaisakh P";
		subject = session.getAvailable_capacity()+ " Slots Vaccine Available "+session.getMin_age_limit() +"+ ( Pin : "+user.getPincode()+" Date : "+session.getDate()+" )";
		toEmail = user.getEmail();
		return this;
	}
}
