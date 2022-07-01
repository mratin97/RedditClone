import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentDTO } from 'src/Model/CommentDTO';
import { Post } from 'src/Model/Post';
import { HomeService } from '../home.service';
import { AuthenticationServiceService } from '../servisi.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  post=new Post();
  n?:number;
  comments?:CommentDTO[];
  constructor(private activeRoute: ActivatedRoute,private homeService:HomeService, private authenticationService: AuthenticationServiceService,private route: Router) { }

  ngOnInit(): void {
  
    this.activeRoute.data.subscribe((data:{post?:Post}) => {
      if(data.post==undefined || data.post== null){

        console.error();

      } 

      console.log(data.post);
      this.post=data.post  ? data.post : new Post();;
      this.homeService.getComment(data.post).subscribe((comments) => (this.comments=comments));
     

    })
  }


  public showKarma(post:Post){
       

    
       
}
removeComment(commenDTO:CommentDTO){;
  this.homeService.deleteComment(commenDTO).subscribe((result) => {
    this.route.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.onSameUrlNavigation = 'reload';
    this.route.navigate(['/home']);
  
  } ,(error)=>{
  console.log(error);
  });

}

 public openPost(post: Post){
  
      
    this.homeService.getComment(post).subscribe((comments) => (this.comments=comments));
    this.route.navigate([`/post/${post?.id}`]);
  
  
  }

  public showAddComment(): void {
    console.log("a");
    let x = document.getElementById("addComment");
    
    if (x!.style.display === "none") {
      x!.style.display = "block";
      
    } else {
      x!.style.display = "none";
      
    }
}

commentDTO1:CommentDTO=new CommentDTO();
addComment(){
 
  this.commentDTO1.post=this.post;
  console.log(this.commentDTO1.post);

  this.homeService.addComment(this.commentDTO1).subscribe((result) => {
    if(result){
      this.commentDTO1=new CommentDTO();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  }); 
}



public openComment(commentDTO1: CommentDTO){
  
      
  this.homeService.getComment(commentDTO1).subscribe((comments) => (this.comments=comments));
  this.route.navigate([`/comment/${commentDTO1?.id}`]);


}


public showEditPost(): void {
  console.log("a");
  let x = document.getElementById("editPost");
  if (x!.style.display === "none") {
    x!.style.display = "block";
  } else {
    x!.style.display = "none";
  }
}
editPost(post:any){
  
  
  this.homeService.editPost(post).subscribe((result) => {
    if(result){
      this.post=new Post();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  });
}



public upKarma(commenDTO:CommentDTO){
  
  console.log(commenDTO);
  this.homeService.upKarmaComment(commenDTO).subscribe((result) => {
    if(result){
      this.commentDTO1=new CommentDTO();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  });
  
}
public downKarma(commenDTO:CommentDTO){
  this.homeService.downKarmaComment(commenDTO).subscribe((result) => {
    if(result){
      this.commentDTO1=new CommentDTO();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  });
  
}
}
