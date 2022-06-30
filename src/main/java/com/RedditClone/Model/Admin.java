package com.RedditClone.Model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Admin  extends User{


    public Admin(Long id, String username, String password, String email, String avatar, LocalDate registrationDate, String description, String displayName, Role role) {
        super(id, username, password, email, avatar, registrationDate, description, displayName, role);
    }

    public Admin() {
    }
}
