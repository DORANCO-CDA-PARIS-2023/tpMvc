package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.exception.AlreadyRegisteredException;
import com.doranco.coursSpring.model.exception.IncompleteFormException;
import com.doranco.coursSpring.model.exception.InvalidLoginException;
import com.doranco.coursSpring.model.exception.MissMatchPasswordException;
import com.doranco.coursSpring.model.form.LoginForm;
import com.doranco.coursSpring.model.form.RegisterForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    private void checkRegisterForm(RegisterForm registerForm) throws IncompleteFormException {
        if (
                registerForm.getEmail() == null
                        || registerForm.getFirstName() == null
                        || registerForm.getLastName() == null
                        || registerForm.getPassword() == null
                        || registerForm.getConfirmPassword() == null

        ) {
            throw new IncompleteFormException("Le formulaire d'inscription est incomplet.");
        }
    }

    public void register(RegisterForm registerForm)
            throws IncompleteFormException, AlreadyRegisteredException, MissMatchPasswordException {

        checkRegisterForm(registerForm);
        Optional<User> userByEmail = this.userService.getUserByEmail(registerForm.getEmail());

        if (userByEmail.isPresent()) {
            throw new AlreadyRegisteredException("L'email utilisé est déjà associé à un compte.");
        }

        if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
            throw new MissMatchPasswordException("Les mots de passes ne sont pas identiques.");
        }

        User user = new User();
        user.setLastName(registerForm.getLastName());
        user.setFirstName(registerForm.getFirstName());
        user.setEmail(registerForm.getEmail());
        user.setPassword(registerForm.getPassword());
        this.userService.addUser(user);
    }

    private void checkLoginForm(LoginForm loginForm) throws IncompleteFormException {
        if (loginForm.getEmail() == null || loginForm.getPassword() == null) {
            throw new IncompleteFormException("Le formulaire de connexion est incomplet.");
        }
    }

    public User login(LoginForm loginForm) throws IncompleteFormException, InvalidLoginException {
        checkLoginForm(loginForm);
        Optional<User> userByEmail = this.userService.getUserByEmail(loginForm.getEmail());

        if (userByEmail.isEmpty()) {
            throw new InvalidLoginException("L'utilisateur n'existe pas.");
        }

        if (!userByEmail.get().getPassword().equals(loginForm.getPassword())) {
            throw new InvalidLoginException("Mot de passe incorrect.");
        }

        return userByEmail.get();
    }

}
