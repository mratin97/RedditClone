package com.RedditClone.Services;

import com.RedditClone.Model.Moderator;
import com.RedditClone.Model.Role;
import com.RedditClone.Model.User;
import com.RedditClone.Repositorys.ModeratorRepository;
import com.RedditClone.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ModeratorRepository moderatorRepository;


    public void saveModerator(Moderator moderator){


        moderatorRepository.save(moderator);


    }






    }



