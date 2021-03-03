package com.greenfoxacademy.reddit.reddit.modell;

import com.greenfoxacademy.reddit.reddit.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String url;
    private Long timestamp;
    private Integer score;
    @ManyToOne
    private User owner;

    public Post(PostDTO postDTO, User user) {
        this.title = postDTO.getTitle();
        this.url = postDTO.getUrl();
        this.timestamp = System.currentTimeMillis();
        this.score = 0;
        this.owner = user;
    }

    public Post upVote() {
        this.score++;
        return this;
    }

    public Post downVote() {
        this.score--;
        return this;
    }

    public Post modify(PostDTO postDTO) {
        this.title = postDTO.getTitle();
        this.url = postDTO.getUrl();
        return this;
    }
}
