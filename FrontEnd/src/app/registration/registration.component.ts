import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from '../servisi.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private router : Router,
    private authenticationService: AuthenticationServiceService) { }

  ngOnInit(): void {
  }

  registration(form: NgForm): void{
    
    var username: string = form.value.username;
    var password: string = form.value.password;
    var email: string = form.value.email;
    var displayName: string = form.value.displayName;
    var description: string = form.value.description;
   
    

    
    this.authenticationService.register(username, password,description,displayName,email).then(
      (register:boolean) => {
        if(register){
          this.router.navigate([""]);
        }
      }
    ,
    (err:Error) => {
      console.log(err);
    });
  }

}
