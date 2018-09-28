import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import { CookieService } from "ngx-cookie-service";
import {UpdatePasswordRequest} from "../../Models/Request";
import {UpdatePasswordResponse} from "../../Models/Response";

@Component({
  selector: 'app-change-pwd',
  templateUrl: './change-pwd.component.html',
  styleUrls: ['./change-pwd.component.css']
})
export class ChangePwdComponent implements OnInit {

  updatePasswordRequest: UpdatePasswordRequest;

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) { }

  ngOnInit() {
  }

  public updatePassword(oldPass: string, newPass: string){

      const httpHeaders = {
          headers: new HttpHeaders({
              'Content-Type': 'application/json'
          })
      };

      this.updatePasswordRequest = new class implements UpdatePasswordRequest {
          newPassword: string;
          oldPassword: string;
          userId: number;
      };

      this.updatePasswordRequest.newPassword = newPass;
      this.updatePasswordRequest.oldPassword = oldPass;
      this.updatePasswordRequest.userId = +this.cookieService.get("userId");

      this.http.post<UpdatePasswordResponse>("http://localhost:8080/api/rest/user/password/update", this.updatePasswordRequest, httpHeaders).subscribe((data: UpdatePasswordResponse) => {

        this.router.navigate(['']);

      })

  }

}
