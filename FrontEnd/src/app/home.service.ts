import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Community } from 'src/Model/Community';
import { Post } from 'src/Model/Post';
import { CommentDTO } from 'src/Model/CommentDTO';
import { AuthenticationServiceService } from './servisi.service';
import { Reaction } from 'src/Model/Reaction';
import { User } from 'src/Model/User';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor( private authService: AuthenticationServiceService, private httpClient: HttpClient) { }


  private readonly URL = "http://localhost:8081/community";
  private readonly URL1 = "http://localhost:8081/postByCommunityId";
  private readonly URL2 = "http://localhost:8081/post";
  private readonly URL3 = "http://localhost:8081/api/post";
  private readonly URL4 = "http://localhost:8081/postKarma";
  private readonly URL44 = "http://localhost:8081/commentKarma";
  private readonly URL5 = "http://localhost:8081/api/reactUpPost"
  private readonly URL55 = "http://localhost:8081/api/reactUpComment"
  private readonly URL6 = "http://localhost:8081/api/reactDownPost"
  private readonly URL66 = "http://localhost:8081/api/reactDownComment"
  private readonly URL7 = "http://localhost:8081/api/community"
  private readonly URL8 = "http://localhost:8081/api/communityEdit"
  private readonly URL9 = "http://localhost:8081/api/editUser"
  private readonly URL10 = "http://localhost:8081/comment"
  private readonly URL11 = "http://localhost:8081/api/comment"
  private readonly URL12 = "http://localhost:8081/api/postEdit"
  private readonly URL13 = "http://localhost:8081/api/commentEdit"

  getAll(): Observable<Community[]> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.get<Community[]>(this.URL,requestOptions);
  }


  getAllCommunityPosts(community?:Community): Observable<Post[]> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL1}/${community?.id}`;
    return this.httpClient.get<Post[]>(url,requestOptions);
  }

  getCommunity(id : number): Promise<Community> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL}/${id}`;
    return this.httpClient.get<Community>(url,requestOptions).toPromise().then((response ) => {return response},()=>{return Promise.reject()} );
  }



  getPost(id : number): Promise<Post> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL3}/${id}`;
    return this.httpClient.get<Post>(url,requestOptions).toPromise().then((response ) => {return response},()=>{return Promise.reject()} );
  }


  getComment(post?:Post): Observable<CommentDTO[]> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL2}/${post?.id}`;
    return this.httpClient.get<CommentDTO[]>(url,requestOptions);
  }


  getOneComment(id : number): Promise<CommentDTO> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL10}/${id}`;
    return this.httpClient.get<CommentDTO>(url,requestOptions).toPromise().then((response ) => {return response},()=>{return Promise.reject()} );
  }


  getPostKarma(post?:Post): Observable<number> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    console.log(post);
    const url = `${this.URL4}/${post?.id}`;
    return this.httpClient.get<number>(url,requestOptions);
  }
  getCommentKarma(commenDTO?:CommentDTO): Observable<number> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    console.log(commenDTO);
    const url = `${this.URL44}/${commenDTO?.id}`;
    return this.httpClient.get<number>(url,requestOptions);
  }
  addPost(post:Post): Observable<Post>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Post>(this.URL3,post,requestOptions);
  }


  addComment(commentDTO:CommentDTO): Observable<CommentDTO>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Post>(this.URL11,commentDTO,requestOptions);
  }


  addCommunity(community:Community): Observable<Community>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Community>(this.URL7,community,requestOptions);
  }



  editCommunity(community?:Community): Observable<Community>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Community>(this.URL8,community,requestOptions);
  }


  editPost(post?:Post): Observable<Post>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Post>(this.URL12,post,requestOptions);
  }


  editComment(commenDTO?:CommentDTO): Observable<CommentDTO>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<CommentDTO>(this.URL13,commenDTO,requestOptions);
  }


  editUser(user:User): Observable<User>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<User>(this.URL9,user,requestOptions);
  }

  upKarma(post:Post): Observable<Reaction>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Reaction>(this.URL5,post,requestOptions);
  }

  upKarmaComment(commenDTO:CommentDTO): Observable<CommentDTO>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<CommentDTO>(this.URL55,commenDTO,requestOptions);
  }
  downKarmaComment(commenDTO:CommentDTO): Observable<CommentDTO>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<CommentDTO>(this.URL66,commenDTO,requestOptions);
  }

  downKarma(post:Post): Observable<Reaction>{
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    return this.httpClient.post<Reaction>(this.URL6,post,requestOptions);
  }
  deletePost(post:Post): Observable<any> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL2}/${post.id}`;
    return this.httpClient.delete<any>(url, requestOptions);
  }

  deleteCommunity(community:Community): Observable<any> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL1}/${community.id}`;
    return this.httpClient.delete<any>(url, requestOptions);
  }

  deleteComment(commentDTO:CommentDTO): Observable<any> {
    const headInfo = {
      'Content-Type': 'application/json',
      'X-Auth-Token': "" + this.authService.getToken()
      
    }
    const requestOptions = {
      headers: new HttpHeaders(headInfo)
    };
    const url = `${this.URL10}/${commentDTO.id}`;
    return this.httpClient.delete<any>(url, requestOptions);
  }


}




