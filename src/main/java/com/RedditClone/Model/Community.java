package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;


    @Column(name="description")
    private String description;

    @Column(name="creationDate")
    private LocalDate creationDate;

    @Column(name="isSuspended")
    private boolean isSuspended;

    @Column(name="suspendedReason ")
    private String suspendedReason;

    @JsonBackReference(value="ModeratorCommunityRef")
    @ManyToOne
    @JoinColumn(name="Moderator_id", referencedColumnName = "id")
    private Moderator moderator;


    @JsonManagedReference(value="CommunityBanRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Banned> banneds;

    @JsonManagedReference(value="PostCommunityRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Post> posts;


    @JsonManagedReference(value="CommunityRuleRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Rule> rules;


    public Community() {
    }

    public Community(Long id, String name, String description, LocalDate creationDate, boolean isSuspended, String suspendedTeason, Moderator moderator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedTeason;
        this.moderator = moderator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedTeason) {
        this.suspendedReason = suspendedTeason;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }
}
