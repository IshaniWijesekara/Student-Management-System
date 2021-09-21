import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {EmployeeAttendanceModel} from '../model/employee-attendance.model';
import {AttendanceModel} from '../model/attendance.model';

@Injectable({
    providedIn: 'root'
})
export class EmployeeAttendanceService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    /** Get All Attendance Details **/
    getAllAttendace():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/employee_attendance/',  {headers: this.headers});
    }

    /** Save Attendance Details **/
    saveAttendanceDetails(dto: EmployeeAttendanceModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/employee_attendance/markAttendance', dto, { headers: this.headers });
    }

    update(dto: EmployeeAttendanceModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/employee_attendance/update', dto, { headers: this.headers });
    }

    /** Search User Details By ID **/
    getUserById(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/employee_attendance/getUser/' + id, {headers: this.headersJson});
    }

    /** Search Student Details By ID **/
    getById(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/employee_attendance/getAttendance/' + id, {headers: this.headersJson});
    }

    /** Search Attendance Details **/
    searchEmployeeAttedndace(studentDTO: EmployeeAttendanceModel){
        return this.httpClient.post(this.BASE_URL + 'api/employee_attendance/searchEmployeeAttendance', studentDTO, { headers: this.headers });
    }
}
