import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {EditJobResponse, GetMyApplicantsResponse, GetMyJobsResponse} from "../../Models/Response";
import {Job, JobApplication, UserInfo} from "../../Models/Helpers";
import {EditJobRequest} from "../../Models/Request";
import {t} from "@angular/core/src/render3";

@Component({
  selector: 'app-my-jobs',
  templateUrl: './my-jobs.component.html',
  styleUrls: ['./my-jobs.component.css']
})
export class MyJobsComponent implements OnInit {

    private userId: string;
    public myJobs: Job[];
    public applicants: UserInfo[];
    private editJobRequest: EditJobRequest;
    public editJobFlags: boolean[];
    public seeApplicantsFlags: boolean[];
    public jobIndex: number[];

    constructor(private http: HttpClient, private cookieService: CookieService) {

        this.userId = this.cookieService.get("userId");

        this.getMyJobs();
    }

    ngOnInit() {
    }


    public getMyJobs(){

        let i;

        this.http.get<GetMyJobsResponse>("http://localhost:8080/api/rest/job/getmyjobs/" + this.userId).subscribe((data: GetMyJobsResponse) => {

            this.myJobs = data.jobs;

            this.editJobFlags = new Array(data.jobs.length);
            this.seeApplicantsFlags = new Array(data.jobs.length);
            this.jobIndex = new Array(data.jobs.length);

            for(i=0; i<data.jobs.length; i++){

                this.editJobFlags[i] = false;
                this.seeApplicantsFlags[i] = false;
                this.jobIndex[i] = this.myJobs[i].id;

            }

        });

    }

    public getJobApplicants(jobId: number){

        for(let i=0; i<this.myJobs.length; i++){
            this.seeApplicantsFlags[i] = false;
        }

        this.http.get<GetMyApplicantsResponse>("http://localhost:8080/api/rest/job/getapplicants/" + jobId.toString()).subscribe((data: GetMyApplicantsResponse) => {

            this.applicants = data.users;

        })

    }

    public editJob(jobId: number, title: string, description: string) {

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.editJobRequest = new class implements EditJobRequest {
            jobDescription: string;
            jobId: number;
            jobTitle: string;
        };

        this.editJobRequest.jobDescription = description;
        this.editJobRequest.jobTitle = title;
        this.editJobRequest.jobId = jobId;

        this.http.post<EditJobResponse>("http://localhost:8080/api/rest/job/edit", this.editJobRequest, httpHeaders).subscribe((data: EditJobResponse) => {

            this.getMyJobs();
            this.editJobFlags[this.jobIndex.indexOf(jobId)] = false;

        });
    }
}
