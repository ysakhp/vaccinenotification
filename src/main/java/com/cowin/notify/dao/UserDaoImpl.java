package com.cowin.notify.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cowin.notify.model.User;
@Repository
public class UserDaoImpl {
	List<User> list;
	
	
	public List<User> getUsers(){
		User u1 = new User(1, "ysakhpr@gmail.com", 690107);
		User u2 = new User(2, "febamthomas@gmail.com", 689645);
//		User u3 = new User(3,"ysakhpr@gmail.com",382225);
		list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
//		list.add(u3);
		
		return list;
	}
	
	

}
