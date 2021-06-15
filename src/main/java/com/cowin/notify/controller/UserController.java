package com.cowin.notify.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.notify.builder.RestRequestBuilder;
import com.cowin.notify.builder.CowinRequestBuilder;
import com.cowin.notify.model.Center;
import com.cowin.notify.model.User;
import com.cowin.notify.service.UserService;
import com.mysql.cj.log.Log;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	CowinRequestBuilder we;
	
	@PostMapping(value = {"/",""})
	public User addUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping(value = {"/",""})
	public List<User> getUsers( ) {
		return userService.getUsers();
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUserById(@PathVariable(value = "id") Integer userId ) {
		userService.deleteUser(userId);
	}

	@GetMapping("/check")
	public Boolean checkRest() throws IOException, InterruptedException {
		we.getCowinDetails(690107, "12-06-2021");
		 return true;
	}
	
	//http://localhost:8081/api/users/deleteByMailID?email=ysakhpr@gmail.com
	@DeleteMapping("/deleteByMailID")
	public void deleteUserByEmail(@RequestParam(required = false, name = "email") String email) {
		log.info("Delte by mail Id");
		userService.deleteUserByEmail(email);
	}
}
