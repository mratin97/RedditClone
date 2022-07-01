package com.RedditClone.Controllers;

import com.RedditClone.Model.*;
import com.RedditClone.Repositorys.CommentRepository;
import com.RedditClone.Repositorys.ReactionRepository;
import com.RedditClone.Repositorys.UserRepository;
import com.RedditClone.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {





    @Autowired
    CommentService commentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostController postController;

    @Autowired
    ReactionRepository reactionRepository;


    @PostMapping(path = "/api/comment")
    public @ResponseBody ResponseEntity<?> addComment(@RequestBody Comment comment, @AuthenticationPrincipal UserDetails userDetails){
        User user=userRepository.findByUsername(userDetails.getUsername());
        commentService.addComment(comment,user);
        //addReactUpVoteCom(comment,userDetails);

        return new ResponseEntity(comment, HttpStatus.OK);

    };


    @DeleteMapping(path ="/comment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        Comment comment = commentRepository.getById(id);
        if (comment == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        commentService.deleteComment(comment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(path="/comment/{id}")
    public @ResponseBody ResponseEntity<?> getAllCommunity(@PathVariable("id") Long id){

        Optional<Comment> comment = commentRepository.findById(id);

        if(comment.isPresent()){
            return new ResponseEntity<Comment>(comment.get(),HttpStatus.OK);
        }
        return  ResponseEntity.badRequest().build();

    }

    @PostMapping(path = "/api/commentEdit")
    public @ResponseBody ResponseEntity<?> editCommunity(@RequestBody Comment comment ,@AuthenticationPrincipal UserDetails userDetails){
        Comment comment1=commentRepository.getById(comment.getId());
        User user= userRepository.findByUsername(userDetails.getUsername());
        if(user.getId()==comment1.getUser().getId()) {
            commentService.updPost(comment);

            return new ResponseEntity(comment, HttpStatus.OK);
        }
        else  return  ResponseEntity.badRequest().build();




    }




}
