import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {GetNotificationsResponse} from "../../Models/Response";
import {Notification} from "../../Models/Helpers";
import {Router} from "@angular/router";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

    private userId: string;
    public notifications: Notification[] = [];

    constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) {

        this.userId = this.cookieService.get("userId");

        this.getNotifications();

    }

    ngOnInit() {
    }

    private getNotifications() {

        this.http.get<GetNotificationsResponse>("http://localhost:8080/api/rest/notifications/get/" + this.userId).subscribe((data: GetNotificationsResponse) => {

            this.notifications = data.notifications;

        });

    }

    public seeUser(userFrom: number){

        this.router.navigate(['external_profile'], {queryParams: {userId: userFrom}});

    }

    public seePost(postId: number){

        this.router.navigate(['post'], {queryParams: {postId: postId}});

    }


}
