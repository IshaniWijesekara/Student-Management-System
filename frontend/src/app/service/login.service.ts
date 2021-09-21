import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})
export class LoginService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    // /** Login Details **/
    // login(loginDTO: LoginModel): Observable<Object> {
    //     return this.httpClient.post(this.BASE_URL + 'api/login/checkUser', loginDTO, { headers: this.headersJson });
    // }
    //
    // /** Forgot Password **/
    // forgotPassword(loginDTO: LoginModel): Observable<Object> {
    //     return this.httpClient.post(this.BASE_URL + 'api/login/forgotPassword', loginDTO, { headers: this.headersJson });
    // }
    //
    // /** Save User Details **/
    // saveUserDetails(userDTO: UserModel): Observable<Object> {
    //     return this.httpClient.post(this.BASE_URL + 'api/login/saveUser', userDTO, { headers: this.headersJson });
    // }
}
