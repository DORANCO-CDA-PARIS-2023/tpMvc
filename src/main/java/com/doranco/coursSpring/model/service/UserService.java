package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final List<User> userList = new ArrayList<>();;

    public UserService()
    {
    	User t = new User("t", "t", "test@test.test", "t");
    	userList.add(t);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void add(User user)
    {
        userList.add(user);
    }

    public void delete(User user)
    {
        userList.remove(user);
    }

    public User findByEmailAndPassword(String email, String password)
    {
        for(User user : userList)
        {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))  {
                return user;
            }
        }
        return null;
    }
}
