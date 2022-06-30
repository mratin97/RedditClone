package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Moderator")
public class Moderator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;



    @JsonBackReference(value ="UserModeratorRef")
    @ManyToOne
    @JoinColumn(name="User_id", referencedColumnName = "id")
    private User user;

    public Moderator(Long id, User user) {
        this.id = id;
        this.user = user;
    }


    public Moderator() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
