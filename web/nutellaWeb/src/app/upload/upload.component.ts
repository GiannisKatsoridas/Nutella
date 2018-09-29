import { Component } from '@angular/core';
import {HttpClient, HttpEventType, HttpHeaders} from '@angular/common/http';
import {UploadFileResponse} from "../Models/Response";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
	
	selectedFile: File = null;
	private userId: string;

	constructor(private http: HttpClient, private cookieService: CookieService) {

		this.userId = cookieService.get("userId");

	}

	onFileSelected(event) {
		this.selectedFile = event.target.files[0];
	}

	onUpload() {

		const fd = new FormData();
		const httpHeaders = {
		    headers: new HttpHeaders({
                'Content-Type': undefined
            })
        };

		console.log(this.selectedFile);

		fd.append('file', this.selectedFile, this.selectedFile.name);
		//fd.append('user', JSON.stringify(this.userId), "user");

		this.http.post('http://localhost:8080/api/rest/image/upload', fd).subscribe((data: UploadFileResponse) => {

		    console.log(data);

        });


            /*{
		reportProgress: true, observe:'events'
		}).subscribe(events => {
			if (event.type === HttpEventType.UploadProgress) {
				console.log('Upload Progress: ' + Math.round(event.loaded/ event.total *100) + '%')
			}
			else if (event.type === HttpEventType.Response) {
				console.log(event)
			}
		});*/
	}
}
