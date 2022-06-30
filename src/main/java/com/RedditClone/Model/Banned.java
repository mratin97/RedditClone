package com.RedditClone.Model;




import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Banned")
public class Banned {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="timeStamp")
    private LocalDate timeStamp;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="Moderator_id", referencedColumnName = "id")
    private Moderator  moderator;

    @JsonBackReference(value="CommunityBanRef")
    @ManyToOne
    @JoinColumn(name="Community_id", referencedColumnName = "id")
    private Community community;

    @JsonBackReference(value="UserBanRef")
    @ManyToOne
    @JoinColumn(name="User_id", referencedColumnName = "id")
    private User user;


    public Banned(Long id, LocalDate timeStamp, Moderator moderator, Community community, User user) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.moderator = moderator;
        this.community = community;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
