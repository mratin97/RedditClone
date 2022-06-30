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
  commentDTO?:CommentDTO;
  constructor(private activeRoute: ActivatedRoute,private homeService:HomeService, private authenticationService: AuthenticationServiceService,private route: Router ) { }
  
  ngOnInit(): void {

    this.activeRoute.data.subscribe((data:{commentDTO?:CommentDTO}) => {
      if(data.commentDTO==undefined || data.commentDTO== null){

        console.error();

      } 

      console.log(data.commentDTO);
      this.commentDTO=data.commentDTO;
 

    })

    


  }
  }
