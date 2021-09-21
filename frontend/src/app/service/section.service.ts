import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Section } from 'app/model/section.model';
import { Observable } from 'rxjs';

const URL = '/section';

@Injectable({
    providedIn: 'root'
})
export class SectionService {

    private headers;
    public BASE_URL = 'http://localhost:2244/api';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    searchSection(): Observable<Array<Section>> {
        return this.httpClient.get<Array<Section>>(this.BASE_URL + '/section/getSections',  {headers: this.headers});
    }

    saveSection(section: Section): Observable<boolean> {
        return this.httpClient.post<boolean>(this.BASE_URL + URL, section);
      }

      getAllSections(): Observable<Array<Section>> {
        return this.httpClient.get<Array<Section>>(this.BASE_URL + URL);
      }

      deleteSection(id: number): Observable<boolean> {
        return this.httpClient.delete<boolean>(this.BASE_URL + URL + '/' + id);
      }

}
