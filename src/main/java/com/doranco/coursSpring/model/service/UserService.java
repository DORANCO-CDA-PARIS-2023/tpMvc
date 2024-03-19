package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        User tintin = new User("John", "Smith", "test@gmail.fr");
        users.add(tintin);

    }
    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void deleteUser (int id){

    }
}