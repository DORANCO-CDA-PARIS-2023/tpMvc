package com.doranco.coursSpring.model.service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.doranco.coursSpring.model.entity.Auth.AuthForm;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.exception.EmptyFormException;
import com.doranco.coursSpring.model.service.exception.InvalidPasswordException;
import com.doranco.coursSpring.model.service.exception.MismatchPasswordException;
import com.doranco.coursSpring.model.service.exception.NotFoundUserException;
import com.doranco.coursSpring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
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
        String passwordHashed = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, formData.getPassword().toCharArray());
        User newUser = new User(
                formData.getLastName(),
                formData.getFirstName(),
                formData.getEmail(),
                passwordHashed
        );
        userRepository.save(newUser);
    }

    public User login(AuthForm formData) throws EmptyFormException, NotFoundUserException, InvalidPasswordException {
        if (formData.getPassword().isEmpty() || formData.getEmail().isEmpty()) {
            throw new EmptyFormException("Login form fields empty");
        }
        User user = userRepository.findByEmail(formData.getEmail());
        if (user == null) {
            throw new NotFoundUserException("");
        }
        BCrypt.Result result = BCrypt.verifyer().verify(
                formData.getPassword().toCharArray(),
                user.getPassword().toCharArray());
        if (!result.verified) {
            throw new InvalidPasswordException();
        }
        return user;
    }
}
