package com.doranco.coursSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.coursSpring.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
//	public User createUser(User user);
	public User findUserByEmail(String email);
	
//	User createUser(String lastName, String firstName, String userName, String password, String email);
//	
//  	User findByUserName(String userName);
//	
//	User findByEmail(String email);
//	
//	User findByUserNameAndPassword(String userName, String password);
//	
//	User findByEmailAndPassword(String email, String password);
//	
//	User findByUserNameOrEmail(String userName, String email);
//	
//	
//	User updateUser(String lastName, String firstName, String userName, String password, String email);
//	
//	User deleteUser(String userName);
//	
//	User findByRole(String role);
//	
//	List<User> findAll();
	


}
