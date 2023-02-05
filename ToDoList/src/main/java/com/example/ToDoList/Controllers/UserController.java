package com.example.ToDoList.Controllers;

import com.example.ToDoList.Exceptions.NotFoundException;
import com.example.ToDoList.Exceptions.UsernameAlreadyInUseException;
import com.example.ToDoList.Models.User;
import com.example.ToDoList.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser) throws UsernameAlreadyInUseException {
        return userService.createUser(newUser);
    }

    @GetMapping("/userById")
    public Optional<User> getUserById(@RequestParam Long id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/userByUsername")
    public User getUserByUsername(@RequestParam String username) throws NotFoundException {
        return userService.getUserByUsername(username);
    }

    @PutMapping()
    public User updateUser(@RequestBody User newUser) throws NotFoundException, UsernameAlreadyInUseException {
        return userService.updateUser(newUser);
    }

    @DeleteMapping()
    public void deleteUser(Long id) throws NotFoundException {
        userService.deleteUser(id);
    }
}
