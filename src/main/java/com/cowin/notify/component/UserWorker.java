package com.cowin.notify.component;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cowin.notify.model.User;
import com.cowin.notify.service.UserService;

@Component
@Scope("prototype")
public class UserWorker implements Callable {

	Logger log = LoggerFactory.getLogger(UserWorker.class);

	@Autowired
	UserService userService;

	@Autowired
	RestAPIWorker restAPIWorker;

	@Override
	public Vector<User> call() throws Exception {
		log.info("User Data Fetching start in USer Worker");
		Vector<User> users = new Vector<User>(userService.getUsers());
		return users;
	}

	

}
