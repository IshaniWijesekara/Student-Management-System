import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {PaymentModel} from '../model/payment.model';
import {Observable} from 'rxjs/Observable';
import {LecturerModel} from '../model/lecturer.model';

@Injectable({
    providedIn: 'root'
})
export class LecturerService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    /** Save Lecturer Details **/
    saveLecture(lecturerDTO: LecturerModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/lecturer/save', lecturerDTO, { headers: this.headersJson });
    }

    /** Update Lecturer Details **/
    updateLecture(lecturerDTO: LecturerModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/lecturer/update', lecturerDTO, { headers: this.headersJson });
    }

    /** Get All Lecturer Details **/
    getAllLecturer():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/lecturer/',  {headers: this.headers});
    }

    /** Get All Subject Details **/
    getAllSubjects():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/lecturer/getSubjects',  {headers: this.headers});
    }

    /** Get Details By Id **/
    getDetailsById(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/lecturer/getDetails/' + id, {headers: this.headersJson});
    }
}
