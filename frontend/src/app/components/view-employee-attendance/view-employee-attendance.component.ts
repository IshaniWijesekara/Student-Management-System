import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {EmployeeAttendanceModel} from '../../model/employee-attendance.model';
import {Router} from '@angular/router';
import {EmployeeAttendanceService} from '../../service/employee-attendance-service';
import {UserRoleMode} from '../../model/user-role.mode';
import {UserService} from '../../service/user.service';
import swal from 'sweetalert2';
import {AttendanceModel} from '../../model/attendance.model';
@Component({
  selector: 'app-view-employee-attendance',
  templateUrl: './view-employee-attendance.component.html',
  styleUrls: ['./view-employee-attendance.component.css']
})
export class ViewEmployeeAttendanceComponent implements OnInit {
    public studentForm: FormGroup;
    public employeeAttendances: EmployeeAttendanceModel[] = [];
    public result: EmployeeAttendanceModel;
    public userRoles: UserRoleMode[] = [];
  constructor(private router: Router,
              private attendanceService: EmployeeAttendanceService,
              private userService: UserService) { }

  ngOnInit(): void {
      this.studentForm = new FormGroup({
          studentId: new FormControl(null),
          studentId1: new FormControl(null),
          userRole: new FormControl(null),
          date: new FormControl(null),
          inTime: new FormControl(null),
          outTime: new FormControl(null),
          empName: new FormControl(null),
          empNic: new FormControl(null),
          empRole: new FormControl(null),
      });
      this.getAllUserRoles();
      this.getAllAttendance();
  }

    getAllUserRoles() {
        this.userService.getAllUserRoles().subscribe(
            (res: UserRoleMode[]) => {
                this.userRoles = res;
            }
        );
    }

    getAllAttendance(){
      this.attendanceService.getAllAttendace().subscribe(
          (res: EmployeeAttendanceModel[]) => {
            this.employeeAttendances = res;
          }
      )
    }

    onSubmitSearch() {
        const dto = new EmployeeAttendanceModel(0,null,null,
            null,this.studentForm.value.empName,this.studentForm.value.empNic,this.studentForm.value.empRole);
        this.attendanceService.searchEmployeeAttedndace(dto).subscribe(
            (res: EmployeeAttendanceModel[]) => {
                this.employeeAttendances = res;
            }
        )
    }

    addAttendance() {
      const dto = new EmployeeAttendanceModel(0,this.studentForm.value.inTime,this.studentForm.value.outTime,
          this.studentForm.value.date,this.studentForm.value.studentId1,null,this.studentForm.value.userRole);

      this.attendanceService.saveAttendanceDetails(dto).subscribe(
          (res:boolean) => {
              if (res === true) {
                  swal.fire('Success', 'Details submitted successfully', 'success');
                  this.getAllAttendance();
              } else {
                  swal.fire('Sorry!', 'Something went wrong...!', 'error');
              }
          }
      )
    }

    getDetails(){
      this.attendanceService.getUserById(this.studentForm.value.studentId).subscribe(
          (res: EmployeeAttendanceModel) => {
            this.result = res;
              this.studentForm.patchValue(
                  {
                      studentId1: this.result.user,
                      userRole: this.result.role,
                  }
              );
          }
      )
    }

    updateStudent(id: number) {
        this.router.navigate(['/view-employee-attendance/', id , 'update']);
    }

    viewStudent(id: number) {
        this.router.navigate(['/view-employee-attendance/', id , 'view']);
    }
}
