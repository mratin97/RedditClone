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

    this.activeRoute.data.subscribe((data:{commenDTO?:CommentDTO}) => {
      if(data.commenDTO==undefined || data.commenDTO== null){

        console.error();

      } 

      console.log(data.commenDTO);
      this.commentDTO=data.commenDTO;
 

    })

    


  }
  }
