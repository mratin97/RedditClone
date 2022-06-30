package com.RedditClone.Repositorys;

import com.RedditClone.Model.Banned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedRepository extends JpaRepository<Banned, Long> {

}
