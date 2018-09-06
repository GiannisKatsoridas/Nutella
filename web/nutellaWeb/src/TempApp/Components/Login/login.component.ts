import {Component} from "@angular/core";
import {LoginRequest} from "../../Models/Request";
import {LoginResponse} from "../../Models/Response";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html'
})
export class LoginComponent {

    loginRequest: LoginRequest;
    loginResponse: LoginResponse;
    title: string;

    constructor(private http:HttpClient) {}

    public sendGet(req: LoginRequest){

        return this.http.get<LoginResponse>("http://localhost:8080/api/rest/user/login/" + req.email + "/" + req.password);

    }


    public getLogin(email: string, pass: string) {

        this.loginRequest = new class implements LoginRequest {
            email: string = email;
            password: string = pass;
        };


        this.sendGet(this.loginRequest).subscribe((data: LoginResponse) => {
            this.loginResponse = data;
            if (this.loginResponse.userId == 0){
                this.title = "ERROR - Wrong credentials";
            }
            else if (this.loginResponse.userId == -1){
                this.title = "There was an internal server error";
            }
            else if (this.loginResponse.admin){
                this.title = "Welcome Admin!";
            }
            else{
                this.title = "Welcome user with ID: " + this.loginResponse.userId.toString();
            }
        });
    }
}