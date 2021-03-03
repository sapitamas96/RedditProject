package com.greenfoxacademy.reddit.reddit.services;

import com.greenfoxacademy.reddit.reddit.dto.UserDTO;
import com.greenfoxacademy.reddit.reddit.modell.Post;
import com.greenfoxacademy.reddit.reddit.modell.User;

import java.util.List;

public interface UserService {

    User findByName(String username);

    User add(UserDTO userDTO);

    void saveToOwnerPosts(Post newPost, String username);

    List<User> getUsers();

    List<Post> getPosts(UserDTO userDTO);
}
