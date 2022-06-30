package com.RedditClone.Repositorys;

import com.RedditClone.Model.Community;
import com.RedditClone.Model.Post;
import com.RedditClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {



}
