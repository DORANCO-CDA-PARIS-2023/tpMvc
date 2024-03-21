package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
