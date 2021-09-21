import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from 'app/model/login.model';
import { environment } from 'environments/environment';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;


    constructor(private http: HttpClient, private router: Router) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('token')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
      const headers = new HttpHeaders();
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
        return this.http.post<any>(`${environment.apiUrl}/login/authenticate?username=` + username + '&password=' + password, { headers})
            .pipe(map(user => {

              if (user) {
                this.router.navigate(['/dashboard']);
              }
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.currentUserSubject.next(user);
                return user;
            }));
    }


    isAuthenticated(): boolean {
      if (sessionStorage.getItem('token')) {
          // tslint:disable-next-line:triple-equals
        return sessionStorage.getItem('token') == 'false' ? false : true;
      }
    }


    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('token');
        this.currentUserSubject.next(null);
    }
}
