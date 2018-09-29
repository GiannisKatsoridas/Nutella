import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {
    GetConnectionsResponse,
    GetInfoResponse,
    GetPersonalInfoResponse, PostEducationResponse, PostExperienceResponse, PostSkillResponse,
    UpdateEducationResponse, UpdateExperienceResponse, UpdateSkillResponse
} from "../Models/Response";
import {
    GetConnectionsRequest, PostEducationRequest, PostExperienceRequest, PostSkillRequest,
    UpdateEducationRequest,
    UpdateExperienceRequest,
    UpdateSkillRequest
} from "../Models/Request";
import {Education, Experience, Skill, UserInfo} from "../Models/Helpers";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    private userId: string;
    public name: string = "";
    public education: Education[];
    public skills: Skill[];
    public experience: Experience[];
    public educationList: Education[] = [];
    public skillsList: Skill[] = [];
    public experienceList: Experience[] = [];

    private editEducationRequest: UpdateEducationRequest;
    private editExperienceRequest: UpdateExperienceRequest;
    private editSkillRequest: UpdateSkillRequest;

    private postEducationRequest: PostEducationRequest;
    private postExperienceRequest: PostExperienceRequest;
    private postSkillRequest: PostSkillRequest;

    public editEducationFlag: boolean[];
    public educationIndex: number[];
    public editExperienceFlag: boolean[];
    public experienceIndex: number[];
    public editSkillFlag: boolean[];
    public skillIndex: number[];

    public postEducationFlag: boolean = false;
    public postExperienceFlag: boolean = false;
    public postSkillFlag: boolean = false;


    constructor(private http: HttpClient, private cookieService: CookieService) {

        this.userId = this.cookieService.get("userId");

        this.getMyInfo();

        this.getPersonalInfo();

    }

    ngOnInit() {
    }

    public getMyInfo(){

        this.http.get<GetInfoResponse>("http://localhost:8080/api/rest/user/getinfo/" + this.userId).subscribe((data: GetInfoResponse) => {

            this.name = data.userInfo.firstName + " " + data.userInfo.lastName;

        });

    }

    private getPersonalInfo() {

        let i=0;

        this.http.get<GetPersonalInfoResponse>("http://localhost:8080/api/rest/user/getpersonalinfo/" + this.userId).subscribe((data: GetPersonalInfoResponse) => {

            this.experienceList = data.experience;
            if(this.experienceList != null) {
                this.editExperienceFlag = new Array(this.experienceList.length);
                this.experienceIndex = new Array(this.experienceList.length);
                for (i = 0; i < this.experienceList.length; i++) {
                    this.editExperienceFlag[i] = false;
                    this.experienceIndex[i] = this.experienceList[i].experienceId;
                }
            }

            this.educationList = data.education;
            if(this.educationList != null) {
                this.editEducationFlag = new Array(this.educationList.length);
                this.educationIndex = new Array(this.educationList.length);
                for (i = 0; i < this.educationList.length; i++) {
                    this.editEducationFlag[i] = false;
                    this.educationIndex[i] = this.educationList[i].educationId;
                }
            }

            this.skillsList = data.skills;
            if(this.skillsList != null) {
                this.editSkillFlag = new Array(this.skillsList.length);
                this.skillIndex = new Array(this.skillsList.length);
                for (i = 0; i < this.skillsList.length; i++) {
                    this.editSkillFlag[i] = false;
                    this.skillIndex[i] = this.skillsList[i].skillId;
                }
            }

        });

    }

    public getEducation() {

        this.experience = [];
        this.skills = [];
        this.education = this.educationList;

    }

    public getExperience() {

        this.skills = [];
        this.education = [];
        this.experience = this.experienceList;

    }

    public getSkills() {

        this.education = [];
        this.experience = [];
        this.skills = this.skillsList;

    }

    public editEducation(educationId: number, degree: string, institution: string, yearFrom: string, yearTo: string){

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.editEducationRequest = new class implements UpdateEducationRequest {
            degree: string;
            educationId: number;
            institution: string;
            yearFrom: string;
            yearTo: string;
        };

        this.editEducationRequest.degree = degree;
        this.editEducationRequest.educationId = educationId;
        this.editEducationRequest.institution = institution;
        this.editEducationRequest.yearFrom = yearFrom;
        this.editEducationRequest.yearTo = yearTo;

        this.http.post<UpdateEducationResponse>("http://localhost:8080/api/rest/education/update", this.editEducationRequest, httpHeaders).subscribe((data: UpdateEducationResponse) => {

            this.getPersonalInfo();
            this.education = [];

        })
    }

    public editExperience(experienceId: number, position: string, companyTitle: string, dateFrom: string, dateTo: string){

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.editExperienceRequest = new class implements UpdateExperienceRequest {
            companyTitle: string;
            experienceId: number;
            position: string;
            dateFrom: string;
            dateTo: string;
        };

        this.editExperienceRequest.experienceId = experienceId;
        this.editExperienceRequest.position = position;
        this.editExperienceRequest.companyTitle = companyTitle;
        this.editExperienceRequest.dateFrom = dateFrom;
        this.editExperienceRequest.dateTo = dateTo;

        this.http.post<UpdateExperienceResponse>("http://localhost:8080/api/rest/experience/update", this.editExperienceRequest, httpHeaders).subscribe((data: UpdateExperienceResponse) => {

            this.getPersonalInfo();
            this.experience = [];

        });
    }

    public editSkill(skillId: number, skill: string){

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.editSkillRequest = new class implements UpdateSkillRequest {
            skill: string;
            skillId: number;
        };

        this.editSkillRequest.skill = skill;
        this.editSkillRequest.skillId = skillId;

        this.http.post<UpdateSkillResponse>("http://localhost:8080/api/rest/skill/update", this.editSkillRequest, httpHeaders).subscribe((data: UpdateSkillResponse) => {

            this.getPersonalInfo();
            this.skills = [];

        });
    }

    public postEducation(degree: string, institution: string, yearFrom: string, yearTo: string) {

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.postEducationRequest = new class implements PostEducationRequest {
            degree: string;
            institution: string;
            userId: number;
            yearFrom: string;
            yearTo: string;
        };

        this.postEducationRequest.degree = degree;
        this.postEducationRequest.userId = +this.userId;
        this.postEducationRequest.institution = institution;
        this.postEducationRequest.yearFrom = yearFrom;
        this.postEducationRequest.yearTo = yearTo;

        this.http.post<PostEducationResponse>("http://localhost:8080/api/rest/education/post", this.postEducationRequest, httpHeaders).subscribe((data: PostEducationResponse) => {

            this.getPersonalInfo();
            this.education = [];
            this.postEducationFlag = false;

        });

    }

    public postExperience(position: string, company: string, dateFrom: string, dateTo: string){


        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.postExperienceRequest = new class implements PostExperienceRequest {
            companyTitle: string;
            dateTo: string;
            dateFrom: string;
            position: string;
            userId: number;
        };

        this.postExperienceRequest.userId = +this.userId;
        this.postExperienceRequest.position = position;
        this.postExperienceRequest.companyTitle = company;
        this.postExperienceRequest.dateFrom = dateFrom;
        this.postExperienceRequest.dateTo = dateTo;

        this.http.post<PostExperienceResponse>("http://localhost:8080/api/rest/experience/post", this.postExperienceRequest, httpHeaders).subscribe((data: PostExperienceResponse) => {

            this.getPersonalInfo();
            this.experience = [];
            this.postExperienceFlag = false;

        });
    }

    public postSkill(skill: string){

        const httpHeaders = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };

        this.postSkillRequest = new class implements PostSkillRequest {
            skill: string;
            userId: number;
        };

        this.postSkillRequest.skill = skill;
        this.postSkillRequest.userId = +this.userId;

        this.http.post<PostSkillResponse>("http://localhost:8080/api/rest/skill/post", this.postSkillRequest, httpHeaders).subscribe((data: PostSkillResponse) => {

            this.getPersonalInfo();
            this.skills = [];
            this.postSkillFlag = false;

        });

    }


}
