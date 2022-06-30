import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { ServicesComponent } from './services/services.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommunityComponent } from './community/community.component';
import { HomeComponent } from './home/home.component';
import { SubRedditComponent } from './sub-reddit/sub-reddit.component';
import { RegistrationComponent } from './registration/registration.component';
import { PostComponent } from './post/post.component';
import { CommentDTOComponent } from './comment/comment.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    ServicesComponent,
    LoginComponent,
    CommunityComponent,
    HomeComponent,
    SubRedditComponent,
    RegistrationComponent,
    PostComponent,
    CommentDTOComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
