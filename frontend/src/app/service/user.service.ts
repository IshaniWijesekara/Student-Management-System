import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {UserModel} from '../model/user.model';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    /** Save User Details **/
    saveUserDetails(userDTO: UserModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/user/saveUser', userDTO, { headers: this.headersJson });
    }

    /** Update User Details **/
    updateUserDetails(userDTO: UserModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/user/updateUser', userDTO, { headers: this.headersJson });
    }

    /** Check NIC is Exist **/
    checkNICExist(nic: string): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/user/checkByNIC/' + nic, {headers: this.headersJson});
    }

    /** Check UserName is Exist **/
    checkUserNameExist(nic: string): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/user/checkByUserName/' + nic, {headers: this.headersJson});
    }

    /** Get All Users **/
    getAllUsers():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/user/',  {headers: this.headers});
    }

    /** Get All User Roles **/
    getAllUserRoles():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/user/getUserRoles',  {headers: this.headers});
    }

    /** Get User Details By Id **/
    getUserById(id: string): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/user/getUserById/' + id, {headers: this.headersJson});
    }

}
