package com.cowin.notify.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cowin.notify.model.User;
import com.cowin.notify.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Logger log;

	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
		;
	}
}
