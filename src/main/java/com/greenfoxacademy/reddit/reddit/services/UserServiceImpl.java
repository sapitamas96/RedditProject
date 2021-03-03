package com.greenfoxacademy.reddit.reddit.services;

import com.greenfoxacademy.reddit.reddit.dto.UserDTO;
import com.greenfoxacademy.reddit.reddit.modell.Post;
import com.greenfoxacademy.reddit.reddit.modell.User;
import com.greenfoxacademy.reddit.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User add(UserDTO userDTO) {
        return userRepository.save(new User(userDTO));
    }

    @Override
    public void saveToOwnerPosts(Post newPost, String username) {
        userRepository.save(findByName(username).savePost(newPost));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Post> getPosts(UserDTO userDTO) {
        return findByName(userDTO.getUsername()).getPosts();
    }
}
