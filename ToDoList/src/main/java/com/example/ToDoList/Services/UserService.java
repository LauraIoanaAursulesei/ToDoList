package com.example.ToDoList.Services;

import com.example.ToDoList.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
