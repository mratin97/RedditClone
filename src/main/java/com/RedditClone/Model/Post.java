package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="title")
    private String title;

    @Column(name="text")
    private String text;

    @Column(name="creationDate")
    private LocalDate creationDate;

    @Column(name="imgPath")
    private String imgPath;

    @JsonBackReference(value = "postFlareRef")
    @ManyToOne
    @JoinColumn(name="Flair_id", referencedColumnName = "id")
    private Flair flair;

    @JsonBackReference(value = "PostUserRef")
    @ManyToOne
    @JoinColumn(name="User_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference(value="postRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Comment> comments;

    @JsonManagedReference(value="ReactionPostRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.EAGER

    )
    private List<Reaction> reactions;

    @JsonManagedReference(value="PostReportRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Report> reports;
    @JsonBackReference(value="PostCommunityRef")
    @ManyToOne
    @JoinColumn(name="Community_id", referencedColumnName = "id")
    private Community community;





    public Post(Long id, String title, String text, LocalDate creationDate, String imgPath, Flair flair, User user, Community community) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imgPath = imgPath;
        this.flair = flair;
        this.user = user;
        this.community = community;
    }

    public Post() {

    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Flair getFlair() {
        return flair;
    }

    public void setFlair(Flair flair) {
        this.flair = flair;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
