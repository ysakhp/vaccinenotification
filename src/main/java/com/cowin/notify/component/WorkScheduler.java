package com.cowin.notify.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.cowin.notify.dao.UserDaoImpl;
import com.cowin.notify.model.User;
import com.cowin.notify.service.UserService;

@Component
public class WorkScheduler {

	@Autowired
	private ThreadPoolTaskExecutor executor;

	@Autowired
	private Logger log;

	@Autowired
	private Worker worker;

	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Autowired
	private UserService userService;

	private List<User> users = new ArrayList();
	
//	@Scheduled(cron = "0 0/30 * * * ?")
	
	@Scheduled(initialDelay = 1000 , fixedDelay = 1000*60)
	public void startfetchUserThread() {
		/*
		 * log.info("WorkScheduler Worker thread start ");
		 * 
		 * this.users= userService.getUsers();
		 */

	}
	
	@Scheduled(initialDelay = 10000 , fixedDelay = 3000)
	public void startRestRequestThread() {
		
		/*
		 * log.info("StartRestRequest Worker thread start "+users);
		 * 
		 * users.parallelStream().forEach(user -> { worker.setUser(user);
		 * executor.execute(worker); });
		 */
		
	}

	public void removeUser(User user) {
		
		users.remove(user);
	
	}
}
