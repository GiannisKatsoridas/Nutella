import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {UpdateEmailRequest, UpdatePasswordRequest} from "../../Models/Request";
import {UpdateEmailResponse, UpdatePasswordResponse} from "../../Models/Response";

@Component({
  selector: 'app-change-email',
  templateUrl: './change-email.component.html',
  styleUrls: ['./change-email.component.css']
})
export class ChangeEmailComponent implements OnInit {

  updateEmailRequest: UpdateEmailRequest;

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) { }

  ngOnInit() {
  }

  public updateEmail(email: string, password: string){

      const httpHeaders = {
          headers: new HttpHeaders({
              'Content-Type': 'application/json'
          })
      };

      this.updateEmailRequest = new class implements UpdateEmailRequest {
          email: string;
          password: string;
          userId: number;
      };

      this.updateEmailRequest.email = email;
      this.updateEmailRequest.password = password;
      this.updateEmailRequest.userId = +this.cookieService.get("userId");

      this.http.post<UpdateEmailResponse>("http://localhost:8080/api/rest/user/email/update", this.updateEmailRequest, httpHeaders).subscribe((data: UpdateEmailResponse) => {

          this.router.navigate(['']);

      })


  }

}
