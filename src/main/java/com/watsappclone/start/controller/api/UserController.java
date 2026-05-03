package com.watsappclone.start.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public User getUser() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findById(currentUser.getId()).orElse(null);
    }

    @GetMapping("/users")
    public List<User> getallUsers() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Long currentUserId = currentUser.getId();

        List<User> userList = new ArrayList<>();
        userList = userRepository.findAll();

        if (userList == null) {
            return null;
        }

        return userList.stream()
                .filter(user -> !user.getId().equals(currentUserId))
                .toList();
    }
}
