
import { Post } from "./Post";
import { Reaction } from "./Reaction";
import { User } from "./User";

export class CommentDTO{

    id?:number;
    text?:string;
    post?:Post;
    user?:User;
    reaction?:Reaction;
    



}
