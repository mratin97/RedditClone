package com.RedditClone.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
@Entity
@Table(name="Rule")
public class Rule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="description")
    private String description;

    @JsonBackReference(value = "CommunityRuleRef")
    @ManyToOne
    @JoinColumn(name="Community_id", referencedColumnName = "id")
    private Community community;

}
