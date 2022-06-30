import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Community } from 'src/Model/Community';
import { HomeService } from '../home.service';

@Injectable({
  providedIn: 'root'
})
export class CommunityResolver implements Resolve<Community> {
  constructor (private homeService: HomeService,) {}
  resolve(route: ActivatedRouteSnapshot): Promise<Community> {
    const id=route.params["id"]

    return this.homeService.getCommunity(id).then((community) => {return community}, () => {return new Community()})
  }
}
