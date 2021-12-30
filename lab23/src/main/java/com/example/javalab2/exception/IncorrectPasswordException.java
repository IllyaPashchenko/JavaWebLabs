package com.example.javalab2.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("Невірний пароль");
    }
}
