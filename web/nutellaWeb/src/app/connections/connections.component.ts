import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {GetConnectionsRequest} from "../Models/Request";
import {GetConnectionsResponse, GetSearchResultsResponse} from "../Models/Response";
import {UserInfo} from "../Models/Helpers";
import { CookieService } from "ngx-cookie-service";
import {NavbarComponent} from "../navbar/navbar.component";
import {AppModule} from "../app.module";

@Component({
  selector: 'app-connections',
  templateUrl: './connections.component.html',
  styleUrls: ['./connections.component.css']
})
export class ConnectionsComponent implements OnInit {

    public connections: GetConnectionsResponse;
    private getConnectionsRequest: GetConnectionsRequest;
    public getSearchResultsArray: GetSearchResultsResponse;
    public errorMessage: string = "";
    private readonly userId: number;

    constructor(private http: HttpClient, private cookieService: CookieService) {

        this.userId = +this.cookieService.get("userId");

        this.getSearchResultsArray = new class implements GetSearchResultsResponse {
            results: UserInfo[];
        }

        this.connections = new class implements GetConnectionsResponse {
            users: UserInfo[];
        }

        this.getConnections(this.userId);

    }

    ngOnInit() {}


   public getConnections(userId: number) {

        this.getConnectionsRequest = new class implements GetConnectionsRequest {
            userId: number = userId;
        }

        this.http.get<GetConnectionsResponse>("http://localhost:8080/api/rest/user/getconnections/" + this.getConnectionsRequest.userId).subscribe((data: GetConnectionsResponse) => {
            console.log(data);

            this.connections = data;
        });
    }

    public getSearchResults(text: string){

        this.http.get<GetSearchResultsResponse>("http://localhost:8080/api/rest/user/search/" + text).subscribe((data: GetSearchResultsResponse) => {

            this.getSearchResultsArray = data;

            if(this.getSearchResultsArray.results == null)
                this.errorMessage = "No results found!";
            else
                this.errorMessage = "";

        })

    }

    public getUserProfile(userId: number){



    }
}
