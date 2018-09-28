import {ChangeDetectorRef, Component, NgZone, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginRequest} from "../Models/Request";
import {LoginResponse} from "../Models/Response";
import { CookieService } from 'ngx-cookie-service'
import {NavbarComponent} from "../navbar/navbar.component";
import {AppModule} from "../app.module";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest;
  errorMessage: string = null;

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService, private ngZone: NgZone) { }

  ngOnInit() {

  }

  public login(email: string, psw: string){

      this.loginRequest = new class implements LoginRequest {
          email: string = email;
          password: string = psw;
      };

      this.http.get<LoginResponse>("http://localhost:8080/api/rest/user/login/" + this.loginRequest.email + "/" + this.loginRequest.password).subscribe((data: LoginResponse) => {console.log(data.isAdmin);

          if(data.userId == 0){
              this.cookieService.set("isLoggedIn", "false");
              this.errorMessage = "Wrong Credentials!";
              return;
          }

          this.cookieService.set("isLoggedIn", "true");

          if(data.isAdmin){
            this.router.navigate(['admin']);
            return;
          }
          else{
            this.router.navigate(['']);
          }

          this.cookieService.set("userId", data.userId.toString());
          this.cookieService.set("isAdmin", data.isAdmin.toString());


      });

  }

}