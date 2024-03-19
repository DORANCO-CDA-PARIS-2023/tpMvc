package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(
                new User(
                        "Bob",
                        "Bobby",
                        "bob@bobby.com"
                )
        );
        users.add(
                new User(
                        "John",
                        "Dupont",
                        "johnDupont@bobby.com"
                )
        );

        users.add(
                new User(
                        "Franck",
                        "Rodrigue",
                        "franck@rodrigue.com"
                )
        );

    }
}
