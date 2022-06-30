package com.RedditClone.Services;


import com.RedditClone.Model.Comment;
import com.RedditClone.Model.Post;
import com.RedditClone.Model.User;
import com.RedditClone.Repositorys.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;



    public void addComment(Comment comment, User user){

        comment.setUser(user);
        commentRepository.save(comment);

    }

    public void deleteComment(Comment comment){

        commentRepository.delete(comment);

    }


}
