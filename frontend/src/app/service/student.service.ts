import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {StudentDTO} from '../model/student.model';
import {Observable} from 'rxjs/Observable';
import { Student } from 'app/model/students.model';



@Injectable({
    providedIn: 'root'
})
export class StudentService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    /** Update Student Details **/
    updateStudentDetails(studentModel: StudentDTO): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/student/updateStudent', studentModel, { headers: this.headersJson });
    }

    /** Update Details **/
    updateDetails(studentModel: StudentDTO): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/student/update', studentModel, { headers: this.headersJson });
    }

    /** Delete Details **/
    delete(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/student/delete/' + id, { headers: this.headersJson });
    }


    saveStudentDetails(studentModel: Student): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/student/saveStudent', studentModel, { headers: this.headersJson });
    }

    /** Search Student Details By NIC **/
    getStudentByNIC(nic: string): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/student/getStudentByNIC/' + nic, {headers: this.headersJson});
    }

    /** Search Student Details By ID **/
    getStudentById(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/student/getStudent/' + id, {headers: this.headersJson});
    }

    /** Get All Students **/
    getAllStudents():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/student/',  {headers: this.headers});
    }


    getAllStudentsTable(): Observable<Array<Student>>{
        return this.httpClient.get<Array<Student>>(this.BASE_URL + 'api/student/getStudents',  {headers: this.headers});
      }

    /** Search Student Details **/
    searchStudent(studentDTO: StudentDTO){
        return this.httpClient.post(this.BASE_URL + 'api/student/search', studentDTO, { headers: this.headers });
    }

    getAllStudentsSec(): Observable<Array<Student>>{
        return this.httpClient.get<Array<Student>>(this.BASE_URL);
      }
}
