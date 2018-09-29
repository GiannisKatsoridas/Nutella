import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {CommentResponse, GetInfoResponse, GetPostResponse, GetPostsResponse, LikeResponse} from "../../Models/Response";
import {Comment, Like} from "../../Models/Helpers";
import {CommentRequest} from "../../Models/Request";

@Component({
  selector: 'app-post',
  templateUrl: './external-post.component.html',
  styleUrls: ['./external-post.component.css']
})
export class ExternalPostComponent implements OnInit {

    private readonly userId: string;
    public postId: string;
    public creator: string;
    public text: string;
    public likes: Like[];
    public comments: Comment[];
    public commentatorsIndex: number[];
    public commentators: string[];
    private commentRequest: CommentRequest;

    constructor(private http: HttpClient, private router: ActivatedRoute, private cookieService: CookieService) {

        this.userId = this.cookieService.get("userId");
        this.postId = this.router.snapshot.queryParams.postId;

        this.getPost();
    }

    ngOnInit() {
    }

    private getPost(){

        this.http.get<GetPostResponse>("http://localhost:8080/api/rest/post/get/" + this.postId).subscribe((data: GetPostResponse) => {

            this.getUserName(data.post.creator);
            this.text = data.post.text;
            this.likes = data.post.likes;
            this.comments = data.post.comments;

            let i = 0;

            this.commentators = new Array(this.comments.length);
            this.commentatorsIndex = new Array(this.comments.length);

            for(let c of this.comments){

                if(!this.commentatorsIndex.includes(c.userId)) {

                    this.commentatorsIndex[i] = c.userId;
                    this.getCommentatorUserName(c.userId, i);

                    i++;
                }
            }
        });

    }

    private getCommentatorUserName(userId: number, pos: number){

        this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + userId).subscribe((data: GetInfoResponse) => {

            this.commentators[pos] = data.userInfo.firstName + " " + data.userInfo.lastName;

        });

    }

    private getUserName(userId: number){

        this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + userId).subscribe((data: GetInfoResponse) => {

            this.creator = data.userInfo.firstName + " " + data.userInfo.lastName;

        });

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
        }

        this.commentRequest.postId = postId;
        this.commentRequest.userId = +this.userId;
        this.commentRequest.text = content;

        this.http.post<CommentResponse>("http://localhost:8080/api/rest/post/comment", this.commentRequest, httpHeaders).subscribe((data: CommentResponse) => {

            this.getPost();

        })
    }

}
