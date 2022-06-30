package com.RedditClone.Repositorys;

import com.RedditClone.Model.Moderator;
import com.RedditClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {


}
