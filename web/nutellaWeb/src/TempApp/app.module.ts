import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './Components/app.component';
import {LoginComponent} from "./Components/Login/login.component";
import {RegisterComponent} from "./Components/Register/register.component";
import {GetusersComponent} from "./Components/GetUsers/getusers.component";

@NgModule({
  declarations: [
      AppComponent,
      LoginComponent,
      RegisterComponent,
      GetusersComponent
  ],
  imports: [
      BrowserModule,
      HttpClientModule
  ],
  providers: [],
  bootstrap: [
      AppComponent,
      LoginComponent,
      RegisterComponent,
      GetusersComponent
  ]
})
export class AppModule { }
