package com.RedditClone.Controllers;

import com.RedditClone.Model.*;
import com.RedditClone.Repositorys.*;
import com.RedditClone.Services.PostService;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
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
public class PostController {


    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    CommunityRepository communityRepository;

    @PostMapping(path = "/api/post")
    public @ResponseBody ResponseEntity<?> addPost(@RequestBody Post post, @AuthenticationPrincipal UserDetails userDetails) {
        Flair flair=new Flair();
        Community community=new Community();
        LocalDate date=LocalDate.now();
        post.setCreationDate(date);
        post.setUser(userRepository.findByUsername(userDetails.getUsername()));
        postRepository.save(post);
        //Post post1 = new Post(2L,"nesto","te777xt",date,"img", null,userRepository.findByUsername(userDetails.getUsername()),community);
       // postService.addPost(post,userRepository.findByUsername(userDetails.getUsername()));
        addReactUpVote(post,userDetails);
        return new ResponseEntity(post, HttpStatus.OK);
    }
    @PostMapping(path = "/api/postEdit")
    public @ResponseBody ResponseEntity<?> editCommunity(@RequestBody Post post ,@AuthenticationPrincipal UserDetails userDetails){
        Post post1=postRepository.getById(post.getId());
        User user= userRepository.findByUsername(userDetails.getUsername());
        if(user.getId()==post1.getUser().getId()) {
            postService.updPost(post);

            return new ResponseEntity(post, HttpStatus.OK);
        }
        else  return  ResponseEntity.badRequest().build();




    }

    @PostMapping(path = "/api/reactUpPost")
    public @ResponseBody ResponseEntity<?> addReactUpVote(@RequestBody Post post, @AuthenticationPrincipal UserDetails userDetails) {
        List<Reaction> reactions=reactionRepository.findAll();
        for(Reaction react : reactions){
            if(react.getUser()==userRepository.findByUsername(userDetails.getUsername()) && react.getPost().getId()==post.getId()){
                if(react.getType()==ReactionType.DOWNVOTE){

                    reactionRepository.delete(react);
                    return new ResponseEntity(react, HttpStatus.OK);
                }
                return  ResponseEntity.badRequest().build();
            }


        }
        Reaction reaction=new Reaction();
        Long postId= post.getId();
        reaction.setPost(postRepository.getById(postId));
        reaction.setTimestamp(LocalDate.now());
        reaction.setUser(userRepository.findByUsername(userDetails.getUsername()));
        reaction.setType(ReactionType.UPVOTE);
        reactionRepository.save(reaction);
        return new ResponseEntity(reaction, HttpStatus.OK);

    }

    @PostMapping(path = "/api/reactDownPost")
    public @ResponseBody ResponseEntity<?> addReactDownVote(@RequestBody Post post, @AuthenticationPrincipal UserDetails userDetails) {
        List<Reaction> reactions=reactionRepository.findAll();
        for(Reaction react : reactions){
            if(react.getUser()==userRepository.findByUsername(userDetails.getUsername()) && react.getPost().getId()==post.getId()){
                if(react.getType()==ReactionType.UPVOTE){

                    reactionRepository.delete(react);
                    return new ResponseEntity(react, HttpStatus.OK);
                }
                return  ResponseEntity.badRequest().build();
            }


        }
        Reaction reaction=new Reaction();
        Long postId= post.getId();
        reaction.setPost(postRepository.getById(postId));
        reaction.setTimestamp(LocalDate.now());
        reaction.setUser(userRepository.findByUsername(userDetails.getUsername()));
        reaction.setType(ReactionType.DOWNVOTE);
        reactionRepository.save(reaction);
        return new ResponseEntity(reaction, HttpStatus.OK);

    }
    @PostMapping(path = "/api/reactDownComment")
    public @ResponseBody ResponseEntity<?> addReactDownVoteCom(@RequestBody Comment comment, @AuthenticationPrincipal UserDetails userDetails) {
        List<Reaction> reactions=reactionRepository.findAll();
        for(Reaction react : reactions){
            if(react.getUser()==userRepository.findByUsername(userDetails.getUsername()) && react.getComment()==comment ){

                if(react.getType()==ReactionType.UPVOTE){

                    reactionRepository.delete(react);
                    return new ResponseEntity(react, HttpStatus.OK);
                }
                return  ResponseEntity.badRequest().build();
            }


        }
        Reaction reaction=new Reaction();
        Long commentId= comment.getId();
        reaction.setComment(commentRepository.getById(commentId));
        reaction.setTimestamp(LocalDate.now());
        reaction.setUser(userRepository.findByUsername(userDetails.getUsername()));
        reaction.setType(ReactionType.DOWNVOTE);
        reactionRepository.save(reaction);
        return new ResponseEntity(reaction, HttpStatus.OK);

    }


    @PostMapping(path = "/api/reactUpComment")
    public @ResponseBody ResponseEntity<?> addReactUpVoteCom(@RequestBody Comment comment, @AuthenticationPrincipal UserDetails userDetails) {
        List<Reaction> reactions=reactionRepository.findAll();
        for(Reaction react : reactions){
            if(react.getUser()==userRepository.findByUsername(userDetails.getUsername())&& react.getComment()==comment){
                if(react.getType()==ReactionType.UPVOTE){

                    reactionRepository.delete(react);
                    return new ResponseEntity(react, HttpStatus.OK);
                }
                return  ResponseEntity.badRequest().build();
            }


        }
        Reaction reaction = new Reaction();
        Long commentId = comment.getId();
        reaction.setComment(commentRepository.getById(commentId));
        reaction.setTimestamp(LocalDate.now());
        reaction.setUser(userRepository.findByUsername(userDetails.getUsername()));
        reaction.setType(ReactionType.UPVOTE);
        reactionRepository.save(reaction);
        return new ResponseEntity(reaction, HttpStatus.OK);

    }

    @GetMapping(path="/userKarma")
    public @ResponseBody ResponseEntity<?> getKarmaUser(@AuthenticationPrincipal UserDetails userDetails){
        List<Reaction> reactions=reactionRepository.findAll();
        int n=0;
        for(Reaction reaction:reactions){

            if(reaction.getUser()==userRepository.findByUsername(userDetails.getUsername())){

                if(reaction.getType()==ReactionType.UPVOTE){
                    n++;
                }else n--;
            }
        }
        return new ResponseEntity(n, HttpStatus.OK);
    }


    @GetMapping(path="/postKarma/{id}")
    public @ResponseBody ResponseEntity<Integer> getKarmaPost(@PathVariable("id") Long id){
        List<Reaction> reactions=reactionRepository.findAll();
        int n=0;
        for(Reaction reaction:reactions){

            if(reaction.getPost()==postRepository.getById(id)){

                if(reaction.getType()==ReactionType.UPVOTE){
                    n++;
                }else n--;
            }
        }
        return new ResponseEntity(n, HttpStatus.OK);
    }

    @GetMapping(path="/commentKarma")
    public @ResponseBody ResponseEntity<?> getKarmaComment(@PathVariable("id") Long id){
        List<Reaction> reactions=reactionRepository.findAll();
        int n=0;
        for(Reaction reaction:reactions){

            if(reaction.getComment()==commentRepository.getById(id)){

                if(reaction.getType()==ReactionType.UPVOTE){
                    n++;
                }else n--;
            }
        }
        return new ResponseEntity(n, HttpStatus.OK);
    }



    @GetMapping(path="/post")
    public @ResponseBody ResponseEntity<?> getAllAdmins(){

        return new ResponseEntity(postRepository.findAll(), HttpStatus.OK);
    }




    @GetMapping(path="/postByCommunityId/{communityId}")
    public @ResponseBody ResponseEntity<?> getAllCommunityPosts(@PathVariable("communityId") long community){

        List<Post> posts=postService.communityPosts(communityRepository.getById(community));


        return new ResponseEntity(posts, HttpStatus.OK);
    }


    @GetMapping(path="/post/{postId}")
    public @ResponseBody ResponseEntity<?> getPostComments(@PathVariable("postId") long post){

        List<Comment> comments=postService.PostComments(postRepository.getById(post));


        return new ResponseEntity(comments, HttpStatus.OK);
    }



    @GetMapping(path="/api/post/{postId}")
    public @ResponseBody ResponseEntity<?> getPost(@PathVariable("postId") long id){

        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            return new ResponseEntity<Post>(post.get(),HttpStatus.OK);
        }
        return  ResponseEntity.badRequest().build();
    }




    @DeleteMapping(path ="/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id,@AuthenticationPrincipal UserDetails userDetails) {
        Post post = postRepository.getById(id);

        if (post == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        }
        if (post.getUser().getId() == userRepository.findByUsername(userDetails.getUsername()).getId()){

        List<Reaction> reactions=reactionRepository.findAll();
        for(Reaction r:reactions){

            if(r.getPost().getId()== post.getId()){

                reactionRepository.delete(r);
            }


        }
            List<Comment> comments=commentRepository.findAll();
            for(Comment c:comments){

                if(c.getPost().getId()== post.getId()){

                    commentRepository.delete(c);
                }


            }


        postService.deletePost(post);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);}

}