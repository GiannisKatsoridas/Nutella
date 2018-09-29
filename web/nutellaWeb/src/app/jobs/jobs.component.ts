import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {Job, JobApplication} from "../Models/Helpers";
import {
    GetJobsResponse,
    GetMyApplicationsResponse,
    GetMyJobsResponse,
    JobApplicationResponse
} from "../Models/Response";
import {JobApplicationRequest} from "../Models/Request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {

    private readonly userId: string;
    public jobsFromFriends: Job[];
    public jobsFromNeighbors: Job[];
    public jobsAlike: Job[];
    private myJobApplications: JobApplication[];
    private applyJob: JobApplicationRequest;

    constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) {

        this.userId = cookieService.get("userId");

        this.getJobs();
        this.getMyApplications();
    }

    ngOnInit() {
    }

    private getJobs(){

        this.http.get("http://localhost:8080/api/rest/job/getjobs/" + this.userId).subscribe((data: GetJobsResponse) => {

            this.jobsFromNeighbors = data.fromNeighbors;
            this.jobsFromFriends = data.fromFriends;
            this.jobsAlike = data.alike;

        })

    }

    public apply(jobId: number){

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.applyJob = new class implements JobApplicationRequest {
            jobId: number;
            userId: number;
        };

        this.applyJob.jobId = jobId;
        this.applyJob.userId = +this.userId;

        this.http.post<JobApplicationResponse>("http://localhost:8080/api/rest/job/apply", this.applyJob, httpHeaders).subscribe((data: JobApplicationResponse) => {

            this.getMyApplications();

            this.router.navigate(['profile']);

        })
    }

    private getMyApplications(){

        this.http.get<GetMyApplicationsResponse>("http://localhost:8080/api/rest/job/getmyapplications/" + this.userId).subscribe((data: GetMyApplicationsResponse) => {

            this.myJobApplications = data.jobs;

            console.log(this.myJobApplications);

        });

    }

    public hasApplied(jobId: number) : boolean {

        for(let app of this.myJobApplications){

            if(jobId == app.job)
                return true;

        }

        return false;

    }

}
