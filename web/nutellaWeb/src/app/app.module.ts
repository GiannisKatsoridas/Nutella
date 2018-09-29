import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import { HttpClientModule } from "@angular/common/http";
import { CookieService } from 'ngx-cookie-service'

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
import { ComposeComponent } from './messages/compose/compose.component';
import { NotificationsComponent } from './navbar/notifications/notifications.component';
import { LogoutComponent } from './logout/logout.component';
import { ExternalProfileComponent } from './profile/external-profile/external-profile.component';
import {ExternalPostComponent} from "./main/external-post/external-post.component";
import { MyJobsComponent } from './jobs/my-jobs/my-jobs.component';

export const appRoutes: Routes = [
    { path: '', component: MainComponent },
    { path: 'profile', component: ProfileComponent},
    { path: 'jobs', component: JobsComponent },
    { path: 'connections', component: ConnectionsComponent },
    { path: 'settings', component: SettingsComponent },
    { path: 'change_pwd', component: ChangePwdComponent },
    { path: 'change_email', component: ChangeEmailComponent },
    { path: 'messages', component: MessagesComponent },
    { path: 'compose', component: ComposeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'notifications', component: NotificationsComponent },
    { path: 'admin', component: AdminComponent },
    { path: 'user_management', component: UserManagementComponent },
    { path: 'external_profile', component: ExternalProfileComponent },
    { path: 'post', component: ExternalPostComponent },
    { path: 'my_jobs', component: MyJobsComponent },
];

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
    LoginComponent,
    ComposeComponent,
    NotificationsComponent,
    LogoutComponent,
    ExternalProfileComponent,
    ExternalPostComponent,
    MyJobsComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {}
