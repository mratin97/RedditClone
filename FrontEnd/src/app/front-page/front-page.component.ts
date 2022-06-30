import { Component, OnInit } from '@angular/core';
import { Community } from 'src/Model/Community';
import { Post } from 'src/Model/Post';
import { CommentDTO } from 'src/Model/CommentDTO';
import { HomeService } from '../home.service';
import { AuthenticationServiceService } from '../servisi.service';
import { Router } from '@angular/router';
import { Reaction } from 'src/Model/Reaction';
import { User } from 'src/Model/User';

@Component({
  selector: 'app-front-page',
  templateUrl: './front-page.component.html',
  styleUrls: ['./front-page.component.css']
})
export class FrontPageComponent implements OnInit {
  communities?:Community[];
  posts?:Post[];
  comments?:CommentDTO[];
  reaction?:Reaction;
  post:Post=new Post();
  n?:number;
  community?:Community;
  user?:User;
  public user1:User=new User();
  public community1:Community=new Community();
  constructor(private homeService:HomeService, private authenticationService: AuthenticationServiceService,private route: Router) { }

  ngOnInit(): void { this.homeService.getAll().subscribe((communities)=>{this.communities=communities})
  }



  public openCommunity(community1: Community){

    console.log(community1);
      this.homeService.getAllCommunityPosts(community1).subscribe((posts) => (this.posts= posts));
      this.route.navigate([`/community/${community1?.id}`]);


}
public showKarma(post:Post){
       

     this.homeService.getPostKarma(post).subscribe((n) => (this.n=n));
        
}

public addCommunityButton(){


  this.route.navigate(["/addCommunity"]);
}


public goToLogin(){


  this.route.navigate(["/login"]);
}

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



}
public logout(){

  this.authenticationService.logout();
}


addPost(community1: Community){
 
  this.post.community=community1;
  console.log(this.post.community);

  this.homeService.addPost(this.post).subscribe((result) => {
    if(result){
      this.post=new Post();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  });
}

editCommunity(community1:Community){
  
  
  this.homeService.addCommunity(community1).subscribe((result) => {
    if(result){
      this.post=new Post();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  });
}
public showAddCommunity(): void {
  console.log("a");
  let x = document.getElementById("addCommunity");
  if (x!.style.display === "none") {
    x!.style.display = "block";
  } else {
    x!.style.display = "none";
  }
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
gotoDetails(id: number): void {
  this.route.navigate(['/addPost', id]);
}

public showEditUser(): void {
  console.log("a");
  let x = document.getElementById("editUser");
  if (x!.style.display === "none") {
    x!.style.display = "block";
  } else {
    x!.style.display = "none";
  }
}

user2?:User;
confirnpass?:string;

editUser(){
  this.user2=this.authenticationService.getCurrentUser();
  this.user1.username=this.user2?.username;
  console.log(this.user2);
  if(this.confirnpass==this.user1.password){
  this.homeService.editUser(this.user1).subscribe((result) => {
    if(result){
      this.confirnpass="";
      this.user=new User();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      this.route.onSameUrlNavigation = 'reload';
      this.route.navigate(['/home']);


    }
  });}
  else console.error("a");
  
}


removePost(post:Post){;
  this.homeService.deletePost(post).subscribe((result) => {
    this.route.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.onSameUrlNavigation = 'reload';
    this.route.navigate(['/home']);
  
  } ,(error)=>{
  console.log(error);
  });

}


removeCommunity(community:Community){;
  this.homeService.deleteCommunity(community).subscribe((result) => {
    this.route.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.onSameUrlNavigation = 'reload';
    this.route.navigate(['/home']);
  
  } ,(error)=>{
  console.log(error);
  });

}



removeComment(commentDTO:CommentDTO){;
  this.homeService.deleteComment(commentDTO).subscribe((result) => {
    this.route.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.onSameUrlNavigation = 'reload';
    this.route.navigate(['/home']);
  
  } ,(error)=>{
  console.log(error);
  });

}



}