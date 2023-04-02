package com.example.ToDoList.Exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
