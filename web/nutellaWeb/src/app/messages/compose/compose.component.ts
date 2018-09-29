import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import {
    GetConnectionsResponse,
    GetSearchResultsResponse,
    SendMessageResponse
} from "../../Models/Response";
import {forEach} from "@angular/router/src/utils/collection";
import {SendMessageRequest} from "../../Models/Request";
import {Router, RouterModule} from "@angular/router";

@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {

    userId: string;
    userTo: number;
    messageRequest: SendMessageRequest;
    errorMessage: string;
    userVerified: boolean = false;

    constructor(private http: HttpClient, private cookieService: CookieService, private routerService: Router) {

        this.userId = cookieService.get("userId");

    }

    ngOnInit() {
    }

    public verifyReceiver(name: string, content: string) {

        this.http.get<GetConnectionsResponse>("http://localhost:8080/api/rest/user/getconnections/" + this.userId).subscribe((data: GetConnectionsResponse) => {

            for (let user of data.users) {

                if (name == user.firstName + " " + user.lastName) {

                    this.userTo = user.userId;
                    this.userVerified = true;
                    this.sendMessage(content);
                    return;

                }

            }

            this.errorMessage = "User not Identified.";
            this.userVerified = false;
        });

    }

    public sendMessage(text: string) {

        if(!this.userVerified)
          return;

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.messageRequest = new class implements SendMessageRequest {
            message: string;
            userFrom: number;
            userTo: number;
        }

        this.messageRequest.message = text;
        this.messageRequest.userFrom = +this.userId;
        this.messageRequest.userTo = +this.userTo;


        this.http.post<SendMessageResponse>("http://localhost:8080/api/rest/message/send", this.messageRequest, httpHeaders).subscribe((data: SendMessageResponse) => {

            this.routerService.navigate(['messages']);

        })

    }
}
