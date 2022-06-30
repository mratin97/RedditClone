package com.RedditClone.Repositorys;

import com.RedditClone.Model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
