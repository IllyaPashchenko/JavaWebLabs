package com.example.javalab2.exception;

public class IncorrectAuthDataException extends RuntimeException{
    public IncorrectAuthDataException() {
        super("Некоректні дані авторизації");
    }
}
