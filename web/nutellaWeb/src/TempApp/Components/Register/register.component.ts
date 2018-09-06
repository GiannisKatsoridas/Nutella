import {HttpClient, HttpHeaders} from "../../../../node_modules/@angular/common/http";
import {RegisterRequest} from "../../Models/Request";
import {RegisterResponse} from "../../Models/Response";
import {Component} from "@angular/core";


@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
})
export class RegisterComponent {

    register: string;
    registerRequest: RegisterRequest;
    registerResponse: RegisterResponse;


    constructor(private http: HttpClient) {
    }


    public postRegister(first: string, last: string, email: string, pass: string, passConf: string, phone: string, image: string) {

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.registerRequest = new class implements RegisterRequest {
            email: string;
            firstName: string;
            image: string;
            lastName: string;
            passConfirm: string;
            password: string;
            phone: string;
        }

        this.registerRequest.email = email;
        this.registerRequest.firstName = first;
        this.registerRequest.lastName = last;
        this.registerRequest.password = pass;
        this.registerRequest.passConfirm = passConf;
        this.registerRequest.image = image;
        this.registerRequest.phone = phone;

        this.http.post<RegisterResponse>("http://localhost:8080/api/rest/user/register", JSON.stringify(this.registerRequest), httpHeaders).subscribe((data: RegisterResponse) => {
            this.registerResponse = data;
            if (this.registerResponse.userId == 0) {
                this.register = "There is already a user with this specific email address!";
            }
            else if (this.registerResponse.userId == -1) {
                this.register = "The passwords given do not match!";
            }
            else if (this.registerResponse.userId == -2) {
                this.register = "There was an internal server error!";
            }
            else {
                this.register = "User inserted successfully!";
            }
        });

    }
}

