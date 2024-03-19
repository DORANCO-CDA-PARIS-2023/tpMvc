package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> getUser(int id) {
        return this.users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public Optional<User> getUserByEmail(String email) {
        return this.users
                .stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public User deleteUser(int id) {
        Optional<User> deletedUser = this.getUser(id);
        if (deletedUser.isPresent()) {
            this.users.remove(deletedUser);
        }
        return deletedUser.get();
    }
}
