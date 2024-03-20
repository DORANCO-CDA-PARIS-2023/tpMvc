package com.doranco.coursSpring.model.service.exception;

public class MismatchPasswordException extends Exception {

    public MismatchPasswordException() {}

    public MismatchPasswordException(String message) {
        super(message);
    }
}