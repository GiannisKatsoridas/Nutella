import { Component, OnInit } from '@angular/core';
import { CookieService } from "ngx-cookie-service";
import {AppModule, loggedIn} from "../app.module";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isLoggedIn: boolean;

  constructor(private cookieService: CookieService) {

    this.isLoggedIn = cookieService.get("isLoggedIn") == "true";

    console.log(this.isLoggedIn);

  }

  ngOnInit() {
  }

  public logout(){

    this.cookieService.delete("userId");
    this.cookieService.set("isLoggedIn", "false");

  }

}
