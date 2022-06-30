package com.RedditClone.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Flair")
public class Flair {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="name")
    private String name;

    @JsonManagedReference(value="postFlareRef")
    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Post> postList;
}
