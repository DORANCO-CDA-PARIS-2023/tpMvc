package com.doranco.coursSpring.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		/*
		 * users.add(new User("Bob", "Bobby", "user_1", "1234", "bob@bobby.com",
		 * UserRoles.ADMIN)); users.add(new User("Bob", "Bobby", "user_2", "1234",
		 * "bob@bobby.com", UserRoles.MODERATOR)); users.add(new User("Bob", "Bobby",
		 * "user_3", "1234", "bob@bobby.com", UserRoles.USER)); users.add(new
		 * User("Bob", "Bobby", "user_4", "1234", "bob@bobby.com", UserRoles.USER));
		 */
	}

//	private int newID() {
//		int id = 0;
//		for (var user : users) {
//			if (user.getId() > id)
//				id = user.getId();
//		}
//		return id + 1;
//	}

	public void addUser(User user) {
//		if (user.getId() == 0)
//			user.setId(newID());
//		this.users.add(user);
		userRepository.save(user);
		

	}

	public List<User> getUsers() {
//		return this.users;
		return userRepository.findAll();
	}

	public User getUser(int id) {
//		for (User user : users) {
//			if (user.getId() == id) {
//				return user;
//			}
//		}
//		return null;
		return userRepository.findById(id).get();
	}

	public User getUerByEMail(String email) {
//		for (User user : users) {
//			if (user.getEmail().equals(email)) {
//				return user;
//			}
//		}
//		return null;
//		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
		return userRepository.findUserByEmail(email);
	}

	public void deleteUser(int id) {
//		User user = getUser(id);
//		users.remove(user);
		userRepository.deleteById(id);
	}

}
