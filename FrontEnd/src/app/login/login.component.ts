import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { AuthenticationServiceService } from '../servisi.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router : Router,
              private authenticationService: AuthenticationServiceService) {}
             

  ngOnInit(): void {
  }


  login(form: NgForm): void{
    
    var username: string = form.value.username;
    var password: string = form.value.password;
    
    
    this.authenticationService.login(username, password).then(
      (loggedIn:boolean) => {
        console.log(loggedIn)
        if(loggedIn){
          this.router.navigate([""]);
        }
      }
    ,
    (err:Error) => {
      console.log(err);
    });
  }

}
