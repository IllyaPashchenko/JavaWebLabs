package com.example.javalab2.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Користувача не знайдено");
    }
}
