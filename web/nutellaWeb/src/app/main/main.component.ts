import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {CommentResponse, GetInfoResponse, GetPostsResponse, LikeResponse} from "../Models/Response";
import {Article} from "../Models/Helpers";
import {CommentRequest, LikeRequest} from "../Models/Request";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

    private readonly userId: string;
    public posts: Article[];
    public commentsShown: boolean[];
    public postsIndex: number[];
    public postCreators: string[];
    public postsLikes: boolean[];
    public commentators: string[];
    public commentatorsIndex: number[];
    private likeRequest: LikeRequest;
    private commentRequest: CommentRequest;

    constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {

        this.userId = this.cookieService.get("userId");

        this.getPosts();

    }

    ngOnInit() {
    }


    private getPosts(){

        let i = 0;
        let comments = 0;

        this.http.get<GetPostsResponse>("http://localhost:8080/api/rest/post/getposts/" + this.userId).subscribe((data: GetPostsResponse) => {

            this.posts = data.articles;

            this.commentsShown = new Array(this.posts.length);
            this.postsIndex = new Array(this.posts.length);
            this.postCreators = new Array(this.posts.length);
            this.postsLikes = new Array(this.posts.length);

            for(let p of this.posts){

                this.commentsShown[i] = false;
                this.postsIndex[i] = p.postId;
                this.getUserName(p.creator, i);

                for(let like of p.likes) {
                    if (like.userId == +this.userId){
                        this.postsLikes[i] = true;
                        break;
                    }
                    else
                        this.postsLikes[i] = false;
                }

                i++;

                comments += p.comments.length;
            }

            this.commentators = new Array(comments);
            this.commentatorsIndex = new Array(comments);

            i=0;

            for(let p of this.posts){

                for(let c of p.comments){

                    if(!this.commentatorsIndex.includes(c.userId)) {

                        this.commentatorsIndex[i] = c.userId;
                        this.getCommentatorUserName(c.userId, i);
                        i++;

                    }
                }

            }

            console.log(this.postCreators);
        })

    }

    private getUserName(userId: number, pos: number){

        this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + userId).subscribe((data: GetInfoResponse) => {

            this.postCreators[pos] = data.userInfo.firstName + " " + data.userInfo.lastName;

        });

    }

    private getCommentatorUserName(userId: number, pos: number){

        this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + userId).subscribe((data: GetInfoResponse) => {

            this.commentators[pos] = data.userInfo.firstName + " " + data.userInfo.lastName;

        });

    }

    public like(postId: number){

        const httpHeaders = {
            headers : new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.likeRequest = new class implements LikeRequest {
            postId: number;
            userId: number;
        };

        this.likeRequest.postId = postId;
        this.likeRequest.userId = +this.userId;

        this.http.post<LikeResponse>("http://localhost:8080/api/rest/post/like", this.likeRequest, httpHeaders).subscribe((data: LikeResponse) => {

            this.getPosts();

        })
    }

    public comment(postId: number, content: string){

        const httpHeaders = {
            headers : new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.commentRequest = new class implements CommentRequest {
            postId: number;
            text: string;
            userId: number;
        };

        this.commentRequest.postId = postId;
        this.commentRequest.userId = +this.userId;
        this.commentRequest.text = content;

        this.http.post<CommentResponse>("http://localhost:8080/api/rest/post/comment", this.commentRequest, httpHeaders).subscribe((data: CommentResponse) => {

            this.getPosts();

        })
    }
}
