package com.RedditClone.Controllers;

import com.RedditClone.Model.Role;
import com.RedditClone.Model.User;
import com.RedditClone.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/user")
    public @ResponseBody ResponseEntity<?> getAllAdmins(){

        return new ResponseEntity(userService.findById(1L), HttpStatus.OK);
    }



    @PostMapping(path = "/api/user")
    public @ResponseBody ResponseEntity<?> addUser(@RequestBody User user/* @AuthenticationPrincipal UserDetails userDetails*/){

        User newUser = userService.saveUser(user);

        return new ResponseEntity(newUser, HttpStatus.OK);

    }


    @PostMapping(path = "/api/editUser")
    public @ResponseBody ResponseEntity<?> editUser(@RequestBody User user/* @AuthenticationPrincipal UserDetails userDetails*/){

        userService.updUser(user);

        return new ResponseEntity(user, HttpStatus.OK);

    }




}
