package com.RedditClone.Services;


import com.RedditClone.Model.Role;
import com.RedditClone.Model.User;
import com.RedditClone.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user){
        user.setRegistrationDate(LocalDate.now());
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User updUser(User user){
            User newUser=userRepository.findByUsername(user.getUsername());


            newUser.setDescription(user.getDescription());
            newUser.setEmail(user.getEmail());
            newUser.setDisplayName(user.getDisplayName());
            newUser.setPassword(user.getPassword());
            return userRepository.save(newUser);

            }


}
