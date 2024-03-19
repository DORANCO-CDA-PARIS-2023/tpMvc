package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users ;

    public UserService() {
        users = new ArrayList<>();
        users.add(
               new User(1, "Bob", "Bobby", "bob@bobby.com"
                )
        );

    }

    private int newID()
    {
        int id = 0;
        for (var user: users)
        {
            if (user.getId() > id)
                id = user.getId();
        }
        return id + 1;
    }

    public void addUser(User user)
    {
        if (user.getId() == 0) {
            user.setId(newID());
        }
        users.add(user);
    }

    public List<User> getUsers()
    {
        return users;
    }

    public User getUser(int id)
    {
        for (User user : users)
        {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public void deleteUser(int id)
    {
        User user = getUser(id);
        users.remove(user);
    }
}