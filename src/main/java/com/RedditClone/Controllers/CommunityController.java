package com.RedditClone.Controllers;

import com.RedditClone.Model.*;
import com.RedditClone.Repositorys.CommunityRepository;
import com.RedditClone.Repositorys.ModeratorRepository;
import com.RedditClone.Repositorys.UserRepository;
import com.RedditClone.Services.CommunityService;
import com.RedditClone.Services.ModeratorService;
import com.RedditClone.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class CommunityController {



    @Autowired
    private CommunityService communityService;

    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModeratorRepository moderatorRepository;


    @GetMapping(path="/community")
    public @ResponseBody ResponseEntity<?> getAllCommunity(){

        return new ResponseEntity(communityRepository.findAll(), HttpStatus.OK);
    }





    @PostMapping(path = "/api/community")
    public @ResponseBody ResponseEntity<?> addCommunity(@RequestBody Community community ,@AuthenticationPrincipal UserDetails userDetails){

        User user= userRepository.findByUsername(userDetails.getUsername());
        user.setRole(Role.ROLE_MODERATOR);

        Moderator moderator= new Moderator(moderatorRepository.count()+1L,user);
        moderatorService.saveModerator(moderator);
        community.setModerator(moderator);
        communityService.saveCommunity(community);
        community.setCreationDate(LocalDate.now());
        return new ResponseEntity(community, HttpStatus.OK);

    }

    @PostMapping(path = "/api/communityEdit")
    public @ResponseBody ResponseEntity<?> editCommunity(@RequestBody Community community ,@AuthenticationPrincipal UserDetails userDetails){
        Community com=communityRepository.getById(community.getId());
        User user= userRepository.findByUsername(userDetails.getUsername());
        if(user.getId()==com.getModerator().getUser().getId()) {
            communityService.editCommunity(community);

            return new ResponseEntity(community, HttpStatus.OK);
        }
        else  return  ResponseEntity.badRequest().build();




    }

    @GetMapping(path="/community/{id}")
    public @ResponseBody ResponseEntity<?> getAllCommunity(@PathVariable("id") Long id){

        Optional<Community> community = communityRepository.findById(id);

        if(community.isPresent()){
            return new ResponseEntity<Community>(community.get(),HttpStatus.OK);
        }
        return  ResponseEntity.badRequest().build();

    }

    @DeleteMapping(path ="/community/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable("id") Long id) {
        Community community = communityRepository.getById(id);
        if (community == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        communityService.deleteCommunity(community);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
