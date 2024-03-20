package com.doranco.coursSpring.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doranco.coursSpring.model.entity.User;

@Service
public class UserService {
	
	private final List<User> users;
	
	public UserService() {
        users = new ArrayList<>();
        users.add(new User("Bob", "Bobby", "user_1", "1234", "bob@bobby.com"));
        users.add(new User("Bob", "Bobby", "user_2", "1234", "bob@bobby.com"));
        users.add(new User("Bob", "Bobby", "user_3", "1234", "bob@bobby.com"));
        users.add(new User("Bob", "Bobby", "user_4", "1234", "bob@bobby.com"));
	}
	
	private int newID() {
		int id = 0;
		for (var user : users) {
			if (user.getId() > id)
				id = user.getId();
		}
		return id + 1;
	}
	
	public void addUser(User user) {
		if (user.getId() == 0)
			user.setId(newID());
		this.users.add(user);
	}
	
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public User getUser(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User getUerByEMail(String email) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
	
	public void deleteUser(int id) {
		User user = getUser(id);
		users.remove(user);
	}

}
