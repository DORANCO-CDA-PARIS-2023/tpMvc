package com.doranco.coursSpring.model.service.exception;

public class NotFoundUserException extends Exception{

    public NotFoundUserException() {}

    public NotFoundUserException(String message) {
        super(message);
    }
}
