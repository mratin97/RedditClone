import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { CommentDTO } from 'src/Model/CommentDTO';
import { HomeService } from '../home.service';

@Injectable({
  providedIn: 'root'
})
export class CommentResolver implements Resolve<CommentDTO> {
  constructor (private homeService: HomeService,) {}
  resolve(route: ActivatedRouteSnapshot): Promise<CommentDTO> {
    const id=route.params["id"]

    return this.homeService.getOneComment(id).then((commentDTO) => {return commentDTO}, () => {return new CommentDTO()})
  }
}
