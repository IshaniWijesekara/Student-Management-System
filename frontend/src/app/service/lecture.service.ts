import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { lecture } from "app/model/lecture.model";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LectureService {

    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    searchLectures(): Observable<Array<lecture>> {
        return this.httpClient.get<Array<lecture>>(this.BASE_URL + 'api/lecture/getLectures',  {headers: this.headers});
    }

}
