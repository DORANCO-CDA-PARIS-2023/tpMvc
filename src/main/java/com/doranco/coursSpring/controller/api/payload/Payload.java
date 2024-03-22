package com.doranco.coursSpring.controller.api.payload;

import java.time.LocalDateTime;

public class Payload {

    private String message;
    private Object content;
    private LocalDateTime dateTime;

    public Payload() {
        dateTime = LocalDateTime.now();
    }

    public Payload(String message) {
        this();
        this.message = message;
    }

    public Payload(String message, Object content) {
        this();
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
