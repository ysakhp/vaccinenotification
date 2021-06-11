package com.cowin.notify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.notify.scheduler.Scheduler;

@RestController
public class NotificationController {
	
	@Autowired
	Scheduler Scheduler;
	
	@GetMapping("/notify")
	public String startNotify() {
		Scheduler.req();
		return "success";
		
	}

}
