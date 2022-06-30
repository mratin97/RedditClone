import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators'
import { JwtUtilsService } from './jwt-util.service';
import { throwError } from 'rxjs';
import { User } from 'src/Model/User';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {

  private readonly loginPath = 'http://localhost:8081/api/login';
  private readonly registerPath1 ='http://localhost:8081/api/user';

  constructor(private http: HttpClient, private jwtUtilsService: JwtUtilsService) { }



  login(username: string, password: string): Promise<boolean> {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    var loginInfo = {
      username: username,
      password: password
    };

    return this.http.post(this.loginPath, JSON.stringify(loginInfo), { headers: headers, responseType:"text" }).toPromise().then((response)=> {
      
     if(response !== undefined) localStorage.setItem('token', response);
                                localStorage.setItem('currentUser', JSON.stringify(loginInfo));
                               
      return true;

    },(err)=>{console.log(err); return false})

  }


  getToken(): String {
    var token = localStorage.getItem('token');
    console.log(token);
    return token ? token : "";
  }

  
  logout(): void {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean {
    if (this.getToken() != '') return true;
    else return false;
  }

  getCurrentUser() {
    var currentUser = localStorage.getItem('currentUser');
    return currentUser ? JSON.parse(currentUser) : User;
  }
  

  register(username: string, password: string, description:string,displayName:string,email:string
    ): Promise<boolean> {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    var loginInfo = {
      username: username,
      password: password,
      description:description,
      displayName:displayName,
      email:email,
     
      
     
    };

    return this.http.post(this.registerPath1, JSON.stringify(loginInfo), { headers: headers, responseType:"json" }).toPromise().then((response)=> {
      
     if(response !== undefined || response!== null ){
       
      
      console.log("succ");
      return true;
    
    
    }else {console.log("fail"); return false}
      

    },(err)=>{console.log(err); return false})

  }



}