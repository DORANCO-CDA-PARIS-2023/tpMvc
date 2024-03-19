package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
    User tintin = new User("tintin", "rintintin", "tintin@gmail.com");
    User Lucho = new User("Lucho", "Luc", "luc@gmail.com");
    users.add(tintin);
    users.add(Lucho);

    }
    public void add(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void deleteUser (int id){

    }
}
