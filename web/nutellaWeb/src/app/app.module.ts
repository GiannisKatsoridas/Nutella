import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MessagesComponent } from './messages/messages.component';
import { JobsComponent } from './jobs/jobs.component';
import { ProfileComponent } from './profile/profile.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ConnectionsComponent } from './connections/connections.component';
import { SettingsComponent } from './settings/settings.component';
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { AdminComponent } from './admin/admin.component';
import { UserManagementComponent } from './admin/user-management/user-management.component';
import { SearchResultsComponent } from './connections/search-results/search-results.component';
import { JobCreateComponent } from './jobs/job-create/job-create.component';
import { JobEditComponent } from './jobs/job-edit/job-edit.component';
import { ProfileEditComponent } from './profile/profile-edit/profile-edit.component';
import { ChangeEmailComponent } from './settings/change-email/change-email.component';
import { ChangePwdComponent } from './settings/change-pwd/change-pwd.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from "@angular/common/http";



@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    JobsComponent,
    ProfileComponent,
    NavbarComponent,
    ConnectionsComponent,
    SettingsComponent,
    RegisterComponent,
    MainComponent,
    AdminComponent,
    UserManagementComponent,
    SearchResultsComponent,
    JobCreateComponent,
    JobEditComponent,
    ProfileEditComponent,
    ChangeEmailComponent,
    ChangePwdComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
