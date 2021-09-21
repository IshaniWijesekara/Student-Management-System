import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {AttendanceSearchModel} from '../model/attendance.search.model';
import {Observable} from 'rxjs/Observable';
import {AttendanceModel} from '../model/attendance.model';

@Injectable({
    providedIn: 'root'
})
export class AttendaneService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    /** Search Attendance Details **/
    searchAttendanceDetails(attendanceDTO: AttendanceSearchModel) {
        return this.httpClient.post(this.BASE_URL + 'api/attendance/search', attendanceDTO, { headers: this.headers });
    }

    /** Get All Attendance Details **/
    getAllAttendace():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/',  {headers: this.headers});
    }

    /** Search Attendance Details By ID **/
    getAttendanceById(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getAttendance/' + id, {headers: this.headers});
    }

    /** Update Attendance Details **/
    updateAttendanceDetails(attendanceModel: AttendanceSearchModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/attendance/updateAttendance', attendanceModel, { headers: this.headersJson });
    }

    /** Save Attendance Details **/
    saveAttendanceDetails(dto: AttendanceModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/attendance/markAttendance', dto, { headers: this.headers });
    }

    /** Get All Class Details **/
    getAllClasses():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/classes/',  {headers: this.headers});
    }

    /** Search Attendance Details **/
    searchAttedndace(studentDTO: AttendanceModel) {
        return this.httpClient.post(this.BASE_URL + 'api/attendance/searchAttendance', studentDTO, { headers: this.headers });
    }

    /** Search Attendance Details By ID **/
    getAttendanceByType(type: string): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getAttendanceByType/' + type, {headers: this.headers});
    }

    /** Print Report Attendance By Month **/
    printAttendanceReportByMonth(classType: number, fromDate: string, toDate: string) {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getAttendanceByMonth/' + classType + '/' + fromDate + '/' + toDate, {responseType: 'arraybuffer'});
    }

    /** Print Report Attendance By Student **/
    printAttendanceReportByStudent(classType: number, studentId: string, fromDate: string, toDate: string) {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getAttendanceByStudent/' + classType + '/' + studentId + '/' + fromDate + '/' + toDate, {responseType: 'arraybuffer'});
    }

    /** Print Report Attendance By Student **/
    printAttendanceReportLate(classType: number, inTime: string, fromDate: string, toDate: string) {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getLateAttendance/' + classType + '/' + inTime + '/' + fromDate + '/' + toDate, {responseType: 'arraybuffer'});
    }

    /** Print Report Attendance By Employee **/
    printEmployeeAttendance(fromDate: string, toDate: string) {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getEmployeeAttendance/' + fromDate + '/' + toDate, {responseType: 'arraybuffer'});
    }

    /** Print Report Payment Profit **/
    printPaymentReport(type: string) {
        return this.httpClient.get(this.BASE_URL + 'api/attendance/getPaymentProfit/' + type, {responseType: 'arraybuffer'});
    }
}
