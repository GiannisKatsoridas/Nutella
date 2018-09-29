import { Component, OnInit } from '@angular/core';
import {GetConnectionsRequest} from "../../Models/Request";
import {GetConnectionsResponse, GetInfoResponse, GetSearchResultsResponse} from "../../Models/Response";
import {UserInfo} from "../../Models/Helpers";
import {User} from "../../Models/Helpers";
import {UsersListResponse} from "../../Models/Response";
import { CookieService } from "ngx-cookie-service";
import {HttpClient} from "../../../../node_modules/@angular/common/http";

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  
  	public users: User[];
    public userInfo: string[];
    public userfirstName: string = "";
    public userlastName: string = "";
    private readonly userId: number;

    constructor(private http: HttpClient, private cookieService: CookieService){
        this.userId = +this.cookieService.get("userId");

        this.getUsersList();
    }

    public getUsersList(){

        this.http.get<UsersListResponse>("http://localhost:8080/api/rest/user/getusers").subscribe((data: UsersListResponse) => {
            this.users = data.usersList;
            this.userInfo = new Array(this.users.length);
            console.log(this.users);
        });

    }


    public getUserInfo(id: number){

        this.http.get("http://localhost:8080/api/rest/user/getinfo/" + id.toString()).subscribe((data: GetInfoResponse) => {
            this.userInfo[this.users.map(x => x.id).indexOf(id)] = data.userInfo.firstName + " " + data.userInfo.lastName + " - Image: " + data.userInfo.image;


        });

    }


    public getIndex(id: number) : number {

        return this.users.map(x => x.id).indexOf(id);

    }

	ngOnInit() {
	}

}
