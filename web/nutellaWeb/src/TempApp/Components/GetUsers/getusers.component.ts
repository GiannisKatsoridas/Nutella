import {Component} from "@angular/core";
import {User} from "../../Models/Helpers";
import {HttpClient} from "../../../../node_modules/@angular/common/http";
import {UserInfoResponse, UsersListResponse} from "../../Models/Response";

@Component({
    selector: 'app-getusers',
    templateUrl: './getusers.component.html',
})
export class GetusersComponent {


    users: User[];
    userInfo: string[];

    constructor(private http: HttpClient){}


    public getUsersList(){

        this.http.get<UsersListResponse>("http://localhost:8080/api/rest/user/getusers").subscribe((data: UsersListResponse) => {
            this.users = data.usersList;
            this.userInfo = new Array(this.users.length);
            console.log(this.users);
        });

    }


    public getUserInfo(id: number){

        this.http.get("http://localhost:8080/api/rest/user/getinfo/" + id.toString()).subscribe((data: UserInfoResponse) => {
            this.userInfo[this.users.map(x => x.id).indexOf(id)] = data.userInfo.firstName + " " + data.userInfo.lastName + " - Image: " + data.userInfo.image;
        })

    }


    public getIndex(id: number) : number {

        return this.users.map(x => x.id).indexOf(id);

    }

}