package com.doranco.coursSpring.controller.api;

import java.time.LocalDateTime;

public class Payload {

    private String message;
    private Object content;
    private LocalDateTime dateTime;

    public Payload() {
        this.dateTime = LocalDateTime.now();
    }

    public Payload(String message, String content) {
        this.message = message;
        this.content = content;
        this.dateTime = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "Payload{" +
                "message='" + message + '\'' +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
