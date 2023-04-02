package com.example.ToDoList.Services;

import com.example.ToDoList.Exceptions.NotFoundException;
import com.example.ToDoList.Exceptions.UsernameAlreadyInUseException;
import com.example.ToDoList.Exceptions.WrongPasswordException;
import com.example.ToDoList.Models.User;
import com.example.ToDoList.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) throws NotFoundException {
        Optional<User> user;
        if (userRepository.findById(id).isEmpty())
            throw new NotFoundException("Id not found");
        else
            user = userRepository.findById(id);
        return user;
    }

    public User getUserByUsername(String username) throws NotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public User createUser(User newUser) throws UsernameAlreadyInUseException {
        if (!isUsernameUnique(newUser.getUsername())) {
            throw new UsernameAlreadyInUseException("Username already in use");
        }
        User userToBeSaved = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .build();
        userRepository.save(userToBeSaved);
        return userToBeSaved;
    }

    public User updateUser(User newUser) throws NotFoundException, UsernameAlreadyInUseException {
        User user = userRepository.findById(newUser.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isUsernameUnique(newUser.getUsername())) {
            throw new UsernameAlreadyInUseException("Username already in use");
        }
        if (newUser.getFirstName() != null)
            user.setFirstName(newUser.getFirstName());
        if (newUser.getLastName() != null)
            user.setLastName(newUser.getLastName());
        if (newUser.getUsername() != null)
            user.setUsername(newUser.getUsername());
        if (newUser.getPassword() != null)
            user.setPassword(newUser.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        userRepository.delete(user);
    }

    public User getUsernameAndPassword(String username, String password) throws NotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new NotFoundException("Username not found"));
        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong password");
        }
        return user;
    }
}
