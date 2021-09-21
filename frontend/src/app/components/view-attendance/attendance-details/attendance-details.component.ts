import { Component, OnInit } from '@angular/core';
import {AttendaneService} from '../../../service/attendane.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {AttendanceSearchModel} from '../../../model/attendance.search.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PatternValidation} from '../../../enum/pattern-validation.enum';
import swal from 'sweetalert2';

@Component({
  selector: 'app-attendance-details',
  templateUrl: './attendance-details.component.html',
  styleUrls: ['./attendance-details.component.css']
})
export class AttendanceDetailsComponent implements OnInit {
  public id: number;
  public type: string;
  public attendanceSearchModel: AttendanceSearchModel;
  public attendanceForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private attendanceService: AttendaneService,
              private router: Router) { }

  ngOnInit(): void {
      this.attendanceForm = new FormGroup({
          sid: new FormControl(null),
          date: new FormControl(null),
          firstName: new FormControl(null),
          middleName: new FormControl(null),
          lastName: new FormControl(null),
          nic: new FormControl(null,[Validators.required , Validators.pattern(PatternValidation.nicValidation)]),
          inTime: new FormControl(null),
          outTime: new FormControl(null),
      });
      this.route.params.subscribe(
          (param: Params) => {
              this.id = param.id;
              this.type = param.type;
          }
      );
      this.getAttendanceDetailById(this.id);
  }

  getAttendanceDetailById(id: number) {
    this.attendanceService.getAttendanceById(this.id).subscribe(
        (res: AttendanceSearchModel) => {
          this.attendanceSearchModel = res;
          this.attendanceForm.value.sid = this.attendanceSearchModel.studentId;
          this.attendanceForm.value.date = this.attendanceSearchModel.date;
          this.attendanceForm.value.firstName = this.attendanceSearchModel.firstName;
          this.attendanceForm.value.middleName = this.attendanceSearchModel.middleName;
          this.attendanceForm.value.lastName = this.attendanceSearchModel.lastName;
          this.attendanceForm.value.nic = this.attendanceSearchModel.nic;
          this.attendanceForm.value.inTime = this.attendanceSearchModel.inTime;
          this.attendanceForm.value.outTime = this.attendanceSearchModel.outTime;
        }
    );
  }

    update() {
        this.attendanceService.updateAttendanceDetails(this.attendanceSearchModel).subscribe(
            (res: AttendanceSearchModel) => {
                this.attendanceSearchModel = res;
                if (this.attendanceSearchModel != null) {
                    swal.fire('Success', 'Details submitted successfully', 'success');
                }else {
                    swal.fire('Sorry!', 'Something went wrong...!', 'error');
                }
            }
        )
    }

    cancel(){
        this.router.navigate(['/attendance/']);
    }

    back() {
        this.router.navigate(['/attendance/']);
    }
}
