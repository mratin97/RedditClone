package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;


@Entity
@Table(name="Reaction")
public class Reaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="type")
    private ReactionType type;



    @Column(name="timestamp")
    private LocalDate timestamp;


    @JsonBackReference(value = "ReactionPostRef")
    @ManyToOne( )
    @JoinColumn(name="Post_id", referencedColumnName = "id")
    private Post post;

    @JsonBackReference(value = "ReactionCommentRef")
    @ManyToOne( )
    @JoinColumn(name="Comment_id", referencedColumnName = "id")
    private Comment comment;


    @JsonBackReference(value="UserReactionRef")
    @ManyToOne
    @JoinColumn(name="User_id", referencedColumnName = "id")
    private User user;


    public Reaction(Long id, ReactionType type, LocalDate timestamp, Post post, Comment comment, User user) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.post = post;
        this.comment = comment;
        this.user = user;
    }

    public Reaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
