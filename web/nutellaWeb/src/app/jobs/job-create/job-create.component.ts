import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {InsertJobRequest} from "../../Models/Request";
import {InsertJobResponse} from "../../Models/Response";

@Component({
  selector: 'app-job-create',
  templateUrl: './job-create.component.html',
  styleUrls: ['./job-create.component.css']
})
export class JobCreateComponent implements OnInit {

    private userId: string;
    public errorMessage: string = "";
    private postJobRequest: InsertJobRequest;

    constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {

        this.userId = this.cookieService.get("userId");

    }

    ngOnInit() {
    }

    public postJob(title: string, description: string, skills: string){

        const httpHeaders = {
            headers : new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.postJobRequest = new class implements InsertJobRequest {
            jobDescription: string;
            jobTitle: string;
            skills: string[];
            userId: number;
        };

        this.postJobRequest.jobDescription = description;
        this.postJobRequest.jobTitle = title;
        this.postJobRequest.skills = skills.split(' ');
        this.postJobRequest.userId = +this.userId;

        this.http.post<InsertJobResponse>("http://localhost:8080/api/rest/job/insert", this.postJobRequest, httpHeaders).subscribe((data: InsertJobResponse) => {

           if(data.jobId <= 0){
               this.errorMessage = "Something went wrong. Please try again. (Perhaps you exceeded the maximun word limit)";
           }

        });
    }

}
