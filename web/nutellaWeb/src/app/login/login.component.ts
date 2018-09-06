import { Component } from '@angular/core';
import {LoginResponse} from "./Response";
import {HttpClient} from "node_modules/@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  loginResp: string;

  constructor(private http:HttpClient) { }

  public sendLoginGet(name: string, pass: string){

    console.log("HEY");

    this.http.get<LoginResponse>("http://localhost:8080/api/rest/user/login/" + name + "/" + pass).subscribe((data: LoginResponse) => {
      console.log(data);
      this.loginResp = data.userId.toString();
    })

  }

}
