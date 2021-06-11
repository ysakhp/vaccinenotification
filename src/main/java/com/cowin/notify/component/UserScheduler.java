package com.cowin.notify.component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cowin.notify.model.User;
import com.cowin.notify.service.UserService;

@Component
public class UserScheduler {

	@Autowired
	ExecutorService executorService;

	@Autowired
	RestAPIWorker restAPIWorker;

	@Autowired
	UserWorker userWorker;

	@Autowired
	ThreadConfig restAPIThread;

	private volatile Vector<User> users;

	Logger log = LoggerFactory.getLogger(UserScheduler.class);

	@Scheduled(initialDelay = 1000, fixedDelay = 1000 * 60*60)
	public synchronized void scheduleGetUsers() {
		log.info("User schedule");
		Future userFuture = executorService.submit(userWorker);
		try {
			log.info("User Detailes Fetched" + userFuture.isDone());

			log.info("User Fetched completely in future");

			users = (Vector<User>) userFuture.get();
			/*
			 * Thread t1 = new Thread(userWorker); t1.setName("User Thread"); t1.start();
			 */

			restAPIWorker.setUsers(users);
//			Future restAPIFuture = executorService.submit(restAPIWorker);
//			restAPIThread.requestAPI(users);

		} catch (InterruptedException e) {
			log.info("Interrupted");
			e.printStackTrace();
		} catch (ExecutionException e) {
			log.info("Exception occured");
			e.printStackTrace();
		}
	}

	@Scheduled(fixedDelay = 1000*15)
	public void scheduleResAPICallForUser() {
		
		log.info("Rest API CALL");
		
		Future restAPIFuture = executorService.submit(restAPIWorker);
		
		
		/*
		 * log.info("User " + users); users.stream().forEach(user -> {
		 * 
		 * 
		 * 
		 * 
		 * try {
		 * 
		 * if ((Boolean) restAPIFuture.get() == true) { log.info("Remove USer ");
		 * users.remove(user); }else { log.info("DOnt Rmove"+); } } catch
		 * (InterruptedException | ExecutionException e) { e.printStackTrace(); }
		 * 
		 * 
		 * });
		 */
		
	}

}
