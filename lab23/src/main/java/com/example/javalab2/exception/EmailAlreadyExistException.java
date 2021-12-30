package com.example.javalab2.exception;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException() {
        super("Користувач з цією поштою вже існує");
    }
}
