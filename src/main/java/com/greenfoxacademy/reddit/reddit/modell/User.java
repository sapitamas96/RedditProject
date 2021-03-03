package com.greenfoxacademy.reddit.reddit.modell;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfoxacademy.reddit.reddit.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @OneToMany
    @JsonBackReference
    private List<Post> posts;

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.posts = new ArrayList<>();
    }

    public User savePost(Post newPost) {
        this.posts.add(newPost);
        return this;
    }
}
