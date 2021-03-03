package com.greenfoxacademy.reddit.reddit.services;

import com.greenfoxacademy.reddit.reddit.dto.PostDTO;
import com.greenfoxacademy.reddit.reddit.modell.Post;
import com.greenfoxacademy.reddit.reddit.modell.User;
import com.greenfoxacademy.reddit.reddit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post save(PostDTO postDTO, String username) {
        User user = userService.findByName(username);
        Post newPost = new Post(postDTO, user);
        postRepository.save(newPost);
        userService.saveToOwnerPosts(newPost, username);
        return newPost;
    }

    @Override
    public Post upVote(Long id) throws Exception {
        return postRepository.save(findById(id).upVote());
    }

    @Override
    public Post downVote(Long id) throws Exception {
        return postRepository.save(findById(id).downVote());
    }

    @Override
    public Post delete(Long id) throws Exception{
        Post tmp = findById(id);
        postRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Post edit(PostDTO postDTO, Long id) throws Exception {
        return postRepository.save(findById(id).modify(postDTO));
    }

    //TODO: create custom exception
    private Post findById(Long id) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            return optionalPost.get();
        }
        throw new Exception();
    }
}
