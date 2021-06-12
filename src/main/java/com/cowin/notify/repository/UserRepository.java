package com.cowin.notify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowin.notify.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
