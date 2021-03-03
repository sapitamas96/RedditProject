package com.greenfoxacademy.reddit.reddit.repository;

import com.greenfoxacademy.reddit.reddit.modell.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAll();
}
