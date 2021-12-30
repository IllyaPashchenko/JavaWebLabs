package com.example.javalab2.exception;

public class NoRightsException extends RuntimeException{
    public NoRightsException() {
        super("Недостатні права для доступу");
    }
}
