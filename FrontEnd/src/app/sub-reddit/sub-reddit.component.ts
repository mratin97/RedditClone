import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Community } from 'src/Model/Community';
import { HomeService } from '../home.service';
import { AuthenticationServiceService } from '../servisi.service';

@Component({
  selector: 'app-sub-reddit',
  templateUrl: './sub-reddit.component.html',
  styleUrls: ['./sub-reddit.component.css']
})
export class SubRedditComponent implements OnInit {

  constructor(private homeService:HomeService, private authenticationService: AuthenticationServiceService,private route: Router) { }

  ngOnInit(): void {
  }


community:Community=new Community();
addCommunity(form: NgForm): void{
    
  var name: string = form.value.name;
  var description: string = form.value.description;
  this.community.description=description;
  this.community.name=name;
  

  console.log(this.community)
  this.homeService.addCommunity(this.community).subscribe((result) => {
    if(result){
      this.community=new Community();
      this.route.routeReuseStrategy.shouldReuseRoute = () => false;
      
    }
  });;
  
}







}
