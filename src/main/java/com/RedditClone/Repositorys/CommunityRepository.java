package com.RedditClone.Repositorys;


import com.RedditClone.Model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
