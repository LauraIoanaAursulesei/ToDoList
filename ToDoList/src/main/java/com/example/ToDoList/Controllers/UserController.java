package com.example.ToDoList.Controllers;

import com.example.ToDoList.Dtos.LoginDto;
import com.example.ToDoList.Exceptions.NotFoundException;
import com.example.ToDoList.Exceptions.UsernameAlreadyInUseException;
import com.example.ToDoList.Exceptions.WrongPasswordException;
import com.example.ToDoList.Models.User;
import com.example.ToDoList.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
        try {
            User user = userService.getUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        } catch (WrongPasswordException e) {
            return new ResponseEntity("Wrong password", HttpStatus.UNAUTHORIZED);
        }
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
