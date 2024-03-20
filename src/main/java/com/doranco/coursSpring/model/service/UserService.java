package com.doranco.coursSpring.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }
    
    public void addUser(User user)
    {
		users.add(user);
    }

    public List<User> getAllUsers()
    {
        return users;
    }

    public User getUserByEmail(String email)
    {
        for (User u : users)
        {
            if (u.getEmail().equalsIgnoreCase(email))
                return u;
        }
        return null;
    }

    public void deleteArticle(String email)
    {
        User user = getUserByEmail(email);
        users.remove(user);
    }
}
