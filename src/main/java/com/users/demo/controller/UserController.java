package com.users.demo.controller;

import java.util.List;

import com.users.demo.entity.User;
import com.users.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("UserController.getAllUsers [Start]");
        List<User> users = userService.getAllUsers();
        log.info("UserController.getAllUsers : {}", users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws RuntimeException {
        log.info("UserController.getUserById [Start] : userId = {}", userId);
        User user = userService.getUserById(userId);
        log.info("UserController.getUserById : {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        log.info("UserController.createUser [Start] : user = {}", user);
        User createdUser = userService.createUser(user);
        log.info("UserController.createUser : {}", createdUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId,
                                           @Valid @RequestBody User user) throws RuntimeException {
        log.info("UserController.updateUser [Start] : userId = {}, user = {}", userId, user);
        User updatedUser = userService.updateUser(userId, user);
        log.info("UserController.updateUser : {}", updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) throws RuntimeException {
        log.info("UserController.deleteUser [Start] : userId = {}", userId);
        userService.deleteUser(userId);
        log.info("UserController.deleteUser [End]");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
