package com.users.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.users.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;

@Slf4j
@Service
public class UserService {

    private final Map<Long, User> userMap = new HashMap<>();

    @Autowired
    private WebClient webClient;

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public User getUserById(Long id) throws RuntimeException {
        log.info("UserService.getUserById : id = {}", id);
        if (!userMap.containsKey(id)) {
            log.error("UserService.getUserById : User not found with id: {}", id);
            throw new RuntimeException("User not found with id: " + id);
        }
        return userMap.get(id);
    }

    public User createUser(User user) {
        log.info("UserService.createUser : {}", user);
        // find new max id + 1
        AtomicLong maxId = new AtomicLong(0);
        userMap.keySet().forEach(id -> {
            if (id > maxId.get()) {
                maxId.set(id);
            }
        });
        Long newId = maxId.incrementAndGet();
        log.info("UserService.createUser : newId = {}", newId);
        user.setId(newId);
        userMap.put(newId, user);
        log.info("UserService.createUser : success");
        return user;
    }

    public User updateUser(Long id, User user) throws RuntimeException {
        log.info("UserService.updateUser : id = {}, user = {}", id, user);
        if (!userMap.containsKey(id)) {
            log.error("UserService.updateUser : User not found with id: {}", id);
            throw new RuntimeException("User not found with id: " + id);
        }
        user.setId(id);
        userMap.put(id, user);
        log.info("UserService.updateUser : success");
        return user;
    }

    public void deleteUser(Long id) throws RuntimeException {
        log.info("UserService.deleteUser : id = {}", id);
        if (!userMap.containsKey(id)) {
            log.error("UserService.deleteUser : User not found with id: {}", id);
            throw new RuntimeException("User not found with id: " + id);
        }
        userMap.remove(id);
        log.info("UserService.deleteUser : success");
    }

    @PostConstruct
    public void init() {
        log.info("UserService.init [Start]");
        List<User> users = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
        log.info("UserService.init : get data from webClient : {}", users);
        if (users == null) {
            log.error("UserService.init : No data found");
            return;
        }
        users.forEach(user -> userMap.put(user.getId(), user));
        log.info("UserService.init [End] : {}", users);
    }
}
