import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {RegisterRequest} from "../Models/Request";
import {RegisterResponse} from "../Models/Response";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerRequest: RegisterRequest;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  public register(first: string, last: string, email: string, password: string, passwordConfirm: string, phone: string, image: string){

    const httpHeaders = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    this.registerRequest = new class implements RegisterRequest {
        email: string = email;
        firstName: string = first;
        image: string = image;
        lastName: string = last;
        passConfirm: string = passwordConfirm;
        password: string = password;
        phone: string = phone;
    }

    this.http.post<RegisterResponse>("http://localhost:8080/api/rest/user/register", JSON.stringify(this.registerRequest), httpHeaders).subscribe((data: RegisterResponse) => {

      console.log(data);
      this.router.navigate(['login']);

    })

  }

}