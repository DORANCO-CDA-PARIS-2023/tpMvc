package com.doranco.coursSpring.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.repository.UserRepository;

@Service
public class UserService {

	private static UserRepository userRepository = null;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public List<User> getUsers() {
//		return this.users;
		return userRepository.findAll();
	}

	public User getUser(int id) {
		return userRepository.findById(id).get();
	}

	static public User getUerByEMail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

}
