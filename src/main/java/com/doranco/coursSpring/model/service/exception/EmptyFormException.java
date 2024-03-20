package com.doranco.coursSpring.model.service.exception;

public class EmptyFormException extends Exception{

    public EmptyFormException() {}

    public EmptyFormException(String message) {
        super(message);
    }
}