import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Post } from 'src/Model/Post';
import { HomeService } from '../home.service';

@Injectable({
  providedIn: 'root'
})
export class PostResolver implements Resolve<Post> {
  constructor (private homeService: HomeService,) {}
  resolve(route: ActivatedRouteSnapshot): Promise<Post> {
    const id=route.params["id"]

    return this.homeService.getPost(id).then((post) => {return post}, () => {return new Post()})
  }
}
