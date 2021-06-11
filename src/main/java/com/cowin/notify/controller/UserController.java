package com.cowin.notify.controller;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.notify.model.User;
import com.cowin.notify.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/")
	public List<User> getUsers( ) {
		return userService.getUsers();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(value = "id") Integer userId ) {
		userService.deleteUser(userId);
	}

}
