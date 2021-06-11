package com.cowin.notify.component;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cowin.notify.model.User;

@Component
public class ThreadConfig {
	
	Logger log = LoggerFactory.getLogger(ThreadConfig.class);
	
	@Autowired
	private RestAPIWorker restAPIWorker;
	
	int i = 2;
	
	public void requestAPI(Vector<User> users) {
		/*
		 * log.info("RestApi Thread"); users.parallelStream().forEach(user -> { Thread
		 * t1 = new Thread(restAPIWorker); t1.setName("Thread "+ i); i = i++;
		 * t1.start();
		 * 
		 * });
		 */
		

		
	}
	

}
