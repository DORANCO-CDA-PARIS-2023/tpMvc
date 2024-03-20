package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Auth.AuthForm;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.exception.EmptyFormException;
import com.doranco.coursSpring.model.service.exception.MismatchPasswordException;
import com.doranco.coursSpring.model.service.exception.NotFoundUserException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService)
    {
        this.userService = userService;
    }

    private void checkRegister(AuthForm formData) throws MismatchPasswordException, EmptyFormException
    {
        if (
                formData.getLastName().isEmpty()
                || formData.getFirstName().isEmpty()
                || formData.getEmail().isEmpty()
                || formData.getPassword().isEmpty()
                || formData.getCpassword().isEmpty()
        ) {
            throw new EmptyFormException("Fields empty");
        }

        if (!formData.getPassword().equals(formData.getCpassword()))
        {
            throw new MismatchPasswordException("Mismatch password");
        }

    }

    public void register(AuthForm formData) throws MismatchPasswordException, EmptyFormException {
        checkRegister(formData);
        User newUser = new User(
                formData.getLastName(),
                formData.getFirstName(),
                formData.getEmail(),
                formData.getPassword()
        );
        userService.add(newUser);
        System.out.println(userService.getUserList().size());
    }

    public User login(AuthForm formData) throws EmptyFormException, NotFoundUserException {
        if (formData.getPassword().isEmpty() || formData.getEmail().isEmpty()) {
            throw new EmptyFormException("Login form fields empty");
        }
        User user = userService.findByEmailAndPassword(formData.getEmail(), formData.getPassword());
        if (user == null) {
            throw new NotFoundUserException("");
        }
        return user;
    }
}