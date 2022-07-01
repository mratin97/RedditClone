package com.RedditClone.Services;

import com.RedditClone.Model.Comment;
import com.RedditClone.Model.Community;
import com.RedditClone.Model.Post;
import com.RedditClone.Model.User;
import com.RedditClone.Repositorys.CommentRepository;
import com.RedditClone.Repositorys.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    public Post savePost(Post post){

        return postRepository.save(post);
    }
    public List<Post> communityPosts(Community community){
        List<Post> posts=postRepository.findAll();
        List<Post> comPosts=new ArrayList<Post>();
        for(Post post:posts){
            if(post.getCommunity().getId().equals(community.getId())){

                comPosts.add(post);


            }


        }
        return comPosts;
    }


    public List<Comment> PostComments(Post post){
        List<Comment> comments=commentRepository.findAll();
        List<Comment> comPosts=new ArrayList<Comment>();
        for(Comment comment:comments){
            if(comment.getPost().getId().equals(post.getId())){

                comPosts.add(comment);


            }


        }
        return comPosts;
    }

    public void addPost(Post post, User user){

        Post newPost= new Post();

        newPost.setFlair(post.getFlair());
        newPost.setCreationDate(LocalDate.now());
        newPost.setText(post.getText());
        newPost.setUser(user);
        newPost.setCommunity(newPost.getCommunity());
        newPost.setImgPath(post.getImgPath());
        newPost.setTitle(post.getTitle());
        postRepository.save(newPost);

    }

    public void updPost(Post post, User user){

        Post newPost= new Post();

        newPost.setFlair(post.getFlair());
        newPost.setCreationDate(post.getCreationDate());
        newPost.setText(post.getText());
        newPost.setUser(user);
        newPost.setCommunity(newPost.getCommunity());
        newPost.setImgPath(post.getImgPath());
        newPost.setTitle(post.getTitle());
        postRepository.save(newPost);

    }

    public void allPosts(){

        postRepository.findAll();

        return;

    }
    public void deletePost(Post post){

            postRepository.delete(post);

    }
}
