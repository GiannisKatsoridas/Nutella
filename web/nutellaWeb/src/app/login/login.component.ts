import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {appRoutes} from "../app.module";
import {LoginRequest} from "../Models/Request";
import {LoginResponse} from "../Models/Response";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest;


  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {

  }

  public login(email: string, psw: string){

      this.loginRequest = new class implements LoginRequest {
          email: string = email;
          password: string = psw;
      };

      this.http.get<LoginResponse>("http://localhost:8080/api/rest/user/login/" + this.loginRequest.email + "/" + this.loginRequest.password).subscribe((data: LoginResponse) => {

        console.log(data);

        if(data.isAdmin){
          this.router.navigate(['messages']);
        }
        else{
          this.router.navigate(['settings']);
        }

      });

  }

}
