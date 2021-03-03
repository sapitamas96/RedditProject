package com.greenfoxacademy.reddit.reddit.controllers;

import com.greenfoxacademy.reddit.reddit.dto.UserDTO;
import com.greenfoxacademy.reddit.reddit.modell.Post;
import com.greenfoxacademy.reddit.reddit.modell.User;
import com.greenfoxacademy.reddit.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.add(userDTO);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/posts")
    public List<Post> getUserPosts(@RequestBody UserDTO userDTO) {
        return userService.getPosts(userDTO);
    }
}
