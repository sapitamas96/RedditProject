package com.greenfoxacademy.reddit.reddit.controllers;

import com.greenfoxacademy.reddit.reddit.dto.PostDTO;
import com.greenfoxacademy.reddit.reddit.modell.Post;
import com.greenfoxacademy.reddit.reddit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody PostDTO postDTO, @RequestHeader("header-name") String username) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Username", username);
        return  ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .body(postService.save(postDTO, username));
    }

    @PutMapping("/{id}/upvote")
    public Post upVote(@PathVariable Long id) throws Exception {
        return postService.upVote(id);
    }

    @PutMapping("/{id}/downvote")
    public Post downVote(@PathVariable Long id) throws Exception {
        return postService.downVote(id);
    }

    @DeleteMapping("/{id}")
    public Post delete(@PathVariable Long id) throws Exception {
        return postService.delete(id);
    }

    @PutMapping("/{id}")
    public Post edit(@RequestBody PostDTO postDTO, @PathVariable Long id) throws Exception {
        return postService.edit(postDTO, id);
    }
}
