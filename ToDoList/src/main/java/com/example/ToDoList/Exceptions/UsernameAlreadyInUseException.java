package com.example.ToDoList.Exceptions;

public class UsernameAlreadyInUseException extends Exception{
    public UsernameAlreadyInUseException(String errorMessage) {
        super(errorMessage);
    }
}
