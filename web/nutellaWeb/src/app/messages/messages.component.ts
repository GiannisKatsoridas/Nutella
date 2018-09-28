import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import {GetConversationsResponse, GetInfoResponse, GetMessagesResponse, SendMessageResponse} from "../Models/Response";
import {Message, UserInfo} from "../Models/Helpers";
import {SendMessageRequest} from "../Models/Request";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  userId: number;
  userTo: string;
  conversations: GetConversationsResponse;
  messages: GetMessagesResponse;
  messageRequest: SendMessageRequest;
  user: string;

  constructor(private http: HttpClient, private cookieService: CookieService) {

    this.messages = new class implements GetMessagesResponse {
        messages: Message[];
    };
    this.conversations = new class implements GetConversationsResponse {
        users: UserInfo[];
    };

    this.userId = +this.cookieService.get("userId");

    this.getConversations();
  }

  ngOnInit() {
  }

  public getConversations(){

    this.http.get<GetConversationsResponse>("http://localhost:8080/api/rest/user/getconversations/" + this.userId).subscribe((data: GetConversationsResponse) => {

      this.conversations = data;
      console.log(data);

    });

  }

  public getMessages(userId: number){

      this.http.get<GetMessagesResponse>("http://localhost:8080/api/rest/message/get/" + this.userId + "/" + userId).subscribe((data: GetMessagesResponse) => {

          this.messages = data;
          console.log(data);
          this.userTo = userId.toString();

          this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + this.userTo).subscribe((data: GetInfoResponse) => {

            this.user = data.userInfo.firstName + " " + data.userInfo.lastName;

          })

      });

  }

  public sendMessage(text: string){

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
      this.messageRequest.userFrom = this.userId;
      this.messageRequest.userTo = +this.userTo;


      this.http.post<SendMessageResponse>("http://localhost:8080/api/rest/message/send", this.messageRequest, httpHeaders).subscribe((data: SendMessageResponse) => {

        this.getMessages(+this.userTo);

      })
  }

}
