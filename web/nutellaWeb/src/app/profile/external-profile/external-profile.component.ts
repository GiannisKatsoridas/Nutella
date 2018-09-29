import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {Education, Experience, Skill, UserInfo} from "../../Models/Helpers";
import {
    AcceptConnectionResponse,
    GetConnectionRequestsResponse,
    GetConnectionsResponse,
    GetInfoResponse, GetPersonalInfoResponse, RejectConnectionResponse,
    SendConnectionResponse
} from "../../Models/Response";
import {ActivatedRoute} from "@angular/router";
import {
    AcceptConnectionRequest,
    GetConnectionsRequest,
    RejectConnectionRequest,
    SendConnectionRequest
} from "../../Models/Request";

@Component({
  selector: 'app-external-profile',
  templateUrl: './external-profile.component.html',
  styleUrls: ['./external-profile.component.css']
})
export class ExternalProfileComponent implements OnInit {

    private readonly userId: string;
    private readonly userShown: string;
    public name: string;
    private connections: GetConnectionsResponse;
    private sendConnectionRequest: SendConnectionRequest;
    private connectionRequests: GetConnectionRequestsResponse;
    public isFriend: boolean;
    public connectionRequestSent: boolean;
    public hasSentConnectionRequestToUser: boolean;
    private experienceList: Experience[];
    private skillsList: Skill[];
    private educationList: Education[];
    public experience: Experience[];
    public education: Education[];
    public skills: Skill[];
    private acceptConnectionRequest: AcceptConnectionRequest;
    private rejectConnectionRequest: RejectConnectionRequest;

    constructor(private http: HttpClient, private cookieService: CookieService, private router: ActivatedRoute) {

        this.userId = cookieService.get("userId");
        this.userShown = this.router.snapshot.queryParams.userId;

        console.log(this.userShown);

        this.getUserInfo();

        this.getConnections();
        this.getConnectionRequestsSent();   // Get connection requests that the shown user has received
        this.getMyConnectionRequests();     // Get connection requests that the user has received
        this.getPersonalInfo();
    }

    ngOnInit() {}

    public sendConnection() {

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.sendConnectionRequest = new class implements SendConnectionRequest {
            receiver: number;
            sender: number;
        };

        this.sendConnectionRequest.receiver = +this.userShown;
        this.sendConnectionRequest.sender = +this.userId;

        this.http.post<SendConnectionResponse>("http://localhost:8080/api/rest/connection/sendrequest", this.sendConnectionRequest, httpHeaders).subscribe((data: SendConnectionResponse) => {

            this.connectionRequestSent = true;

        });

    }

    private getConnections() {

        this.http.get<GetConnectionsResponse>("http://localhost:8080/api/rest/user/getconnections/" + this.userId).subscribe((data: GetConnectionsResponse) => {
            console.log(data);

            this.connections = data;

            for(let friend of this.connections.users){

                if(this.userShown == friend.userId.toString()){

                    this.isFriend = true;
                    return;

                }

            }

            this.isFriend = false;
        });
    }

    private getConnectionRequestsSent() {

        this.http.get<GetConnectionRequestsResponse>("http://localhost:8080/api/rest/connection/getrequests/" + this.userShown).subscribe((data: GetConnectionRequestsResponse) => {
            console.log(data);

            this.connectionRequests = data;

            if(this.connectionRequests == null) {
                this.connectionRequestSent = false;
                return
            }

            for(let user of this.connectionRequests.users){

                console.log(user);
                console.log(this.userId);

                if(this.userId == user.userId.toString()){

                    this.connectionRequestSent = true;
                    return;

                }

            }

            this.connectionRequestSent = false;
        });

    }

    private getMyConnectionRequests() {

        this.http.get<GetConnectionRequestsResponse>("http://localhost:8080/api/rest/connection/getrequests/" + this.userId).subscribe((data: GetConnectionRequestsResponse) => {
            console.log(data);

            for(let user of data.users){

                console.log(user);
                console.log(this.userId);

                if(this.userShown == user.userId.toString()){

                    this.hasSentConnectionRequestToUser = true;
                    return;

                }

            }

            this.hasSentConnectionRequestToUser = false;
        });

    }


    private getUserInfo() {

        this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + this.userShown).subscribe((data: GetInfoResponse) => {

            console.log(data);

            this.name = data.userInfo.firstName + " " + data.userInfo.lastName;

        });

    }

    private getPersonalInfo() {


        this.http.get<GetPersonalInfoResponse>("http://localhost:8080/api/rest/user/getpersonalinfo/" + this.userShown).subscribe((data: GetPersonalInfoResponse) => {

            this.experienceList = data.experience;
            this.educationList = data.education;
            this.skillsList = data.skills;

        });

    }

    public getEducation() {

        this.experience = [];
        this.skills = [];
        this.education = this.educationList;

    }

    public getExperience() {

        this.skills = [];
        this.education = [];
        this.experience = this.experienceList;

    }

    public getSkills() {

        this.education = [];
        this.experience = [];
        this.skills = this.skillsList;

    }


    public acceptConnection() {

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.acceptConnectionRequest = new class implements AcceptConnectionRequest {
            userFromId: number;
            userToId: number;
        };

        this.acceptConnectionRequest.userFromId = +this.userShown;
        this.acceptConnectionRequest.userToId = +this.userId;

        this.http.post<AcceptConnectionResponse>("http://localhost:8080/api/rest/connection/accept", this.acceptConnectionRequest, httpHeaders).subscribe((data: AcceptConnectionResponse) => {

            this.isFriend = true;
            this.hasSentConnectionRequestToUser = false;
            this.connectionRequestSent = false;

        });

    }

    public rejectConnection() {

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.rejectConnectionRequest = new class implements RejectConnectionRequest {
            userFromId: number;
            userToId: number;
        };

        this.rejectConnectionRequest.userFromId = +this.userShown;
        this.rejectConnectionRequest.userToId = +this.userId;

        this.http.post<RejectConnectionResponse>("http://localhost:8080/api/rest/connection/reject", this.rejectConnectionRequest, httpHeaders).subscribe((data: RejectConnectionResponse) => {

            this.isFriend = false;
            this.hasSentConnectionRequestToUser = false;
            this.connectionRequestSent = false;

        });

    }
}
