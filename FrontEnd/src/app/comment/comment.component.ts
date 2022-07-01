import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentDTO } from 'src/Model/CommentDTO';
import { HomeService } from '../home.service';
import { AuthenticationServiceService } from '../servisi.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentDTOComponent implements OnInit {
  commentDTO=new CommentDTO();
  constructor(private activeRoute: ActivatedRoute,private homeService:HomeService, private authenticationService: AuthenticationServiceService,private route: Router ) { }
  
  ngOnInit(): void {

    this.activeRoute.data.subscribe((data:{commentDTO?:CommentDTO}) => {
      if(data.commentDTO==undefined || data.commentDTO== null){

        console.error();

      } 

      console.log(data.commentDTO);
      this.commentDTO=data.commentDTO ? data.commentDTO : new CommentDTO();;
 

    })
    
    


  }
  public showEditComment(): void {
    console.log("a");
    let x = document.getElementById("editComment");
    
    if (x!.style.display === "none") {
      x!.style.display = "block";
      
    } else {
      x!.style.display = "none";
      
    }
  
  }

  editComment(CommentDTO:any){
  
  
    this.homeService.editComment(CommentDTO).subscribe((result) => {
      if(result){
        this.commentDTO=new CommentDTO();
        this.route.routeReuseStrategy.shouldReuseRoute = () => false;
        
      }
    });
  }
  }
