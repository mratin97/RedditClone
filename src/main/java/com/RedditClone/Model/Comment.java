package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Comment")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="text")
    private String text;


    @Column(name="timestamp")
    private LocalDate timestamp;

    @Column(name="isDeleted")
    private boolean isDeleted;

    @JsonBackReference(value="postRef")
    @ManyToOne
    @JoinColumn(name="Post_id", referencedColumnName = "id")
    private Post post;

    @JsonBackReference(value="userRef")
    @ManyToOne
    @JoinColumn(name="User_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference(value="ReactionCommentRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY

    )
    private List<Reaction> reactions;

    public Comment() {
    }


    public Comment(Long id, String text, LocalDate timestamp, boolean isDeleted, Post post, User user, List<Reaction> reactions, List<Report> reports) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.post = post;
        this.user = user;
        this.reactions = reactions;
        this.reports = reports;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    @JsonManagedReference(value="CommentReportRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )



    private List<Report> reports;

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
}
