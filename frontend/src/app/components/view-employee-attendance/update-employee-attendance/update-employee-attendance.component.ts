import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {EmployeeAttendanceModel} from '../../../model/employee-attendance.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {EmployeeAttendanceService} from '../../../service/employee-attendance-service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-update-employee-attendance',
  templateUrl: './update-employee-attendance.component.html',
  styleUrls: ['./update-employee-attendance.component.css']
})
export class UpdateEmployeeAttendanceComponent implements OnInit {
    public id: number;
    public type: string;
    public attendanceForm: FormGroup;
    public dto: EmployeeAttendanceModel;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private service: EmployeeAttendanceService) { }

  ngOnInit(): void {
      this.attendanceForm = new FormGroup({
          sid: new FormControl(null),
          name: new FormControl(null),
          role: new FormControl(null),
          date: new FormControl(null),
          inTime: new FormControl(null),
          outTime: new FormControl(null),
      });
      this.route.params.subscribe(
          (param: Params) => {
              this.id = param.id;
              this.type = param.type;
          }
      );
      this.getAttendanceById();
  }

  getAttendanceById(){
    this.service.getById(this.id).subscribe(
        (res: EmployeeAttendanceModel)=> {
          this.dto = res;
          this.attendanceForm.value.sid = this.id;
          this.attendanceForm.value.name = this.dto.user;
          this.attendanceForm.value.role = this.dto.role;
          this.attendanceForm.value.date = this.dto.date;
          this.attendanceForm.value.inTime = this.dto.inTime;
          this.attendanceForm.value.outTime = this.dto.outTime;
        }
    )
  }

    update() {
        this.service.update(this.dto).subscribe(
            (res: boolean) => {
                if (res === true) {
                    swal.fire('Success', 'Details submitted successfully', 'success');
                    this.router.navigate(['/employee-attendance/']);
                } else {
                    swal.fire('Sorry!', 'Something went wrong...!', 'error');
                }
            }
        )
    }

    cancel(){
        this.router.navigate(['/employee-attendance/']);
    }

    back() {
        this.router.navigate(['/employee-attendance/']);
    }
}
