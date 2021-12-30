package com.example.javalab2.exception;

public class NoUserInSessionException extends RuntimeException{
    public NoUserInSessionException() {
        super("В заданій сесії нема користувача");
    }
}
