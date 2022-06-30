import { CommentDTO } from "./CommentDTO";
import { Community } from "./Community";
import { Reaction } from "./Reaction";

export class Post{


    id?:number;
    title?:string;
    text?:string;
    community?:Community;
    comments?:CommentDTO[];
    reaction?:Reaction;
    
    
    }