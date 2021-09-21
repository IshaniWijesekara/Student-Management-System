import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Classes } from "app/model/classes.model";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ClassesService {

    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    searchClasses(): Observable<Array<Classes>> {
        return this.httpClient.get<Array<Classes>>(this.BASE_URL + 'api/classes/getClasses',  {headers: this.headers});
    }

}
