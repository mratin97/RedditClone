import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentDTO } from 'src/Model/CommentDTO';
import { Community } from 'src/Model/Community';
import { Post } from 'src/Model/Post';
import { HomeService } from '../home.service';
import { AuthenticationServiceService } from '../servisi.service';

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrls: ['./community.component.css']
})
export class CommunityComponent implements OnInit {
  community=new Community();
  constructor(private activeRoute: ActivatedRoute,private homeService:HomeService, private authenticationService: AuthenticationServiceService,private route: Router ) {
    

  }
 
  ngOnInit(): void {

    this.activeRoute.data.subscribe((data:{community?:Community}) => {
      if(data.community==undefined || data.community== null){

        console.error();

      } 

      console.log(data.community);
      this.community=data.community ? data.community : new Community();
      this.homeService.getAllCommunityPosts(data.community).subscribe((posts) => (this.posts= posts));
    

    })

    


  }

  post?:Post;
  comments?:CommentDTO[];
  posts?: Post[];
  n?:number;
  
  public upKarma(post:Post){
  
    console.log(post);
    this.homeService.upKarma(post).subscribe((result) => {
      if(result){
        this.post=new Post();
        this.route.routeReuseStrategy.shouldReuseRoute = () => false;
        
      }
    });
    
  }
  public downKarma(post:Post){
    this.homeService.downKarma(post).subscribe((result) => {
      if(result){
        this.post=new Post();
        this.route.routeReuseStrategy.shouldReuseRoute = () => false;
        
      }
    });
    
  }
  
  public openPost(post: Post){
  
      
    this.homeService.getComment(post).subscribe((comments) => (this.comments=comments));
    this.route.navigate([`/post/${post?.id}`]);
  
  
  }


  public showKarma(post:Post){
       

    this.homeService.getPostKarma(post).subscribe((n) => (this.n=n));
       
}

public openCommunity(community: Community){

  console.log(community);
    this.homeService.getAllCommunityPosts(community).subscribe((posts) => (this.posts= posts));
  



    
}

post1:Post=new Post();
addPost(){
 
  this.post1.community=this.community;
  console.log(this.post1);

  this.homeService.addPost(this.post1).subscribe((result) => {
    if(result){
      this.post1=new Post();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  }); 
}


public showAddPost(): void {
  console.log("a");
  let x = document.getElementById("addPost");
  
  if (x!.style.display === "none") {
    x!.style.display = "block";
    
  } else {
    x!.style.display = "none";
    
  }

}
public showEditCommunity(): void {
  console.log("a");
  let x = document.getElementById("addCommunity");
  if (x!.style.display === "none") {
    x!.style.display = "block";
  } else {
    x!.style.display = "none";
  }
}
  community1?=this.community;
  removePost(post:Post){;
    this.homeService.deletePost(post).subscribe((result) => {
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      this.route.onSameUrlNavigation = 'reload';
      this.route.navigate(['/home']);
    
    } ,(error)=>{
    console.log(error);
    });
  
  }

  getCurrentCommunity(){

    this.activeRoute.data.subscribe((data:{community?:Community}) => {
    if(data.community==undefined || data.community== null){

      console.error();
      this.community1= data.community
    } });
  
    return this.community1
  }
  communityEd?=this.getCurrentCommunity();
  editCommunity(community:any){
  
  
    this.homeService.addCommunity(community).subscribe((result) => {
      if(result){
        this.post=new Post();
        this.route.routeReuseStrategy.shouldReuseRoute = () => false;
        
      }
    });
  }
  }

 


