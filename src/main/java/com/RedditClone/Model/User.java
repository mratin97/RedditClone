package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="avatar")
    private String avatar;

    @Column(name="date")
    private LocalDate registrationDate;

    @Column(name="description")
    private String description;

    @Column(name="displayName")
    private String displayName;


    @Column(name="role")
    private Role role;


    @JsonManagedReference(value="userRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Comment> comments;

    @JsonManagedReference(value="UserBanRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Banned> banneds;


    @JsonManagedReference(value="UserModeratorRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Moderator> moderators;

    @JsonManagedReference(value="PostUserRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Post> postList;

    @JsonManagedReference(value="UserReactionRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Reaction> reactions;






    public User(Long id, String username, String password, String email, String avatar, LocalDate registrationDate, String description, String displayName, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
        this.displayName = displayName;
        this.role = role;
    }

    public User() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
