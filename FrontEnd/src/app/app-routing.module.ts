import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommentDTOComponent } from './comment/comment.component';
import { CommunityComponent } from './community/community.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './login/login.component';
import { PostComponent } from './post/post.component';
import { RegistrationComponent } from './registration/registration.component';
import { CommentResolver } from './resolvers/comment.resolver';
import { CommunityResolver } from './resolvers/community.resolver';
import { PostResolver } from './resolvers/post.resolver';
import { SubRedditComponent } from './sub-reddit/sub-reddit.component';

const routes: Routes = [

  { path: 'home', component:  FrontPageComponent },
  { path: '', component:  FrontPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'addCommunity', component: SubRedditComponent },
  { path: 'community/:id', component: CommunityComponent,resolve:{community:CommunityResolver} },
  { path: 'post/:id', component: PostComponent,resolve:{post:PostResolver} },
  { path: 'comment/:id', component: CommentDTOComponent,resolve:{commentDTO:CommentResolver} },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
