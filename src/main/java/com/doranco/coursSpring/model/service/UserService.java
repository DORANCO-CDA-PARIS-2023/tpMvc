package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users;
    private int idCount;

    public UserService() {
        this.idCount = 1;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        user.setId(idCount++);
        this.users.add(user);
    }

    public User getUser(int id) {
        return this.users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseGet(null);
    }

    public User getUserByEmail(String email) {
        return this.users
                .stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseGet(null);
    }

    public User deleteUser(int id) {
        User deletedUser = this.getUser(id);
        if (deletedUser != null) {
            this.users.remove(deletedUser);
        }
        return deletedUser;
    }
}
