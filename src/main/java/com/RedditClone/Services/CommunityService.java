package com.RedditClone.Services;

import com.RedditClone.Model.Comment;
import com.RedditClone.Model.Community;
import com.RedditClone.Repositorys.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {


    @Autowired
    private CommunityRepository communityRepository;


    public Community saveCommunity(Community community){

        return communityRepository.save(community);
    }

    public void editCommunity(Community community){

        Community community1=communityRepository.getById(community.getId());
        community1.setName(community.getName());
        community1.setDescription(community.getDescription());
        communityRepository.save(community1);

    }


    public void deleteCommunity(Community community){

        communityRepository.delete(community);

    }


}
