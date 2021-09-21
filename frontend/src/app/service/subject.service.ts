import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
import {SubjectModel} from '../model/subject.model';


const URL = '/subject';

@Injectable({
    providedIn: 'root'
})
export class SubjectService {


    public BASE_URL = 'http://localhost:2244/api';


    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    getAllSubjects(): Observable<Array<SubjectModel>> {
        return this.httpClient.get<Array<SubjectModel>>(this.BASE_URL + URL);
    }

    deleteSubject(id: number): Observable<boolean> {
        return this.httpClient.delete<boolean>(this.BASE_URL + URL + '/' + id);
    }

    saveSubject(subject: SubjectModel): Observable<boolean> {
        return this.httpClient.post<boolean>(this.BASE_URL + URL, subject);
    }





}
