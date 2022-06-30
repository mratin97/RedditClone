package com.RedditClone.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Report")
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="reason")
    private ReportReason reason;

    @Column(name="timestamp")
    private LocalDate timestamp;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="User_id1", referencedColumnName = "id")
    private User user;

    @Column(name="accepted")
    private boolean accepted;

    @JsonBackReference(value = "CommentReportRef")
    @ManyToOne
    @JoinColumn(name="Comment_id", referencedColumnName = "id")
    private Comment comment;

    @JsonBackReference(value = "PostReportRef")
    @ManyToOne
    @JoinColumn(name="Post_id", referencedColumnName = "id")
    private Post post;



    public Report(Long id, ReportReason reason, LocalDate timestamp, User user, boolean accepted) {
        this.id = id;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
        this.accepted = accepted;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportReason getReason() {
        return reason;
    }

    public void setReason(ReportReason reason) {
        this.reason = reason;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
