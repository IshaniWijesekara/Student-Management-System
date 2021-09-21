import { Component, OnInit } from '@angular/core';
import {AttendaneService} from '../../service/attendane.service';
import {AttendanceSearchModel} from '../../model/attendance.search.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {StudentDTO} from '../../model/student.model';
import {StudentService} from '../../service/student.service';
import {AttendanceModel} from '../../model/attendance.model';
import swal from 'sweetalert2';
import {PatternValidation} from '../../enum/pattern-validation.enum';
import {ClassModel} from '../../model/class.model';

@Component({
  selector: 'app-view-attendance',
  templateUrl: './view-attendance.component.html',
  styleUrls: ['./view-attendance.component.css']
})
export class ViewAttendanceComponent implements OnInit {
  public attendanceSerchModel: AttendanceSearchModel[] = [];
  public classModel: ClassModel[] = [];
  public studentForm: FormGroup;
  public studentModel: StudentDTO;
  public date: Date;

  constructor(private attendanceService: AttendaneService,
              private router: Router,
              private studentService: StudentService) { }

  ngOnInit(): void {
      this.studentForm = new FormGroup({
          studentId: new FormControl(null),
          name: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.nameValidation)]),
          nic: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.nicValidation)]),
          studentId1: new FormControl(null),
          studentName1: new FormControl(null),
          studentNIC1: new FormControl(null),
          date: new FormControl(null),
          inTime: new FormControl(null),
          outTime: new FormControl(null),
          classType: new FormControl(null),
      });
      this.date = new Date();
    this.getAllAttendances();
    this.getAllClasses();
  }

  getAllClasses() {
      this.attendanceService.getAllClasses().subscribe(
          (data: ClassModel[]) => {
              this.classModel = data;
          }
      )
  }

  getAllAttendances() {
    this.attendanceService.getAllAttendace().subscribe(
        (data: AttendanceSearchModel[]) => {
          this.attendanceSerchModel = data;
        }
    )
  }

  getStudentDetails() {
      this.studentService.getStudentById(this.studentForm.value.studentId1).subscribe(
            (res: StudentDTO) => {
                this.studentModel = res;
                this.studentForm.patchValue(
                    {
                        studentName1: this.studentModel.firstName + ' ' + this.studentModel.middleName + ' ' + this.studentModel.lastName,
                        studentNIC1: this.studentModel.nic,
                    }
                );
            }
        )
  }

    addAttendance() {
      const attendanceDTO = new AttendanceModel(0, this.studentForm.value.inTime, this.studentForm.value.outTime, this.studentForm.value.date,
          this.studentForm.value.studentId1,this.studentForm.value.classType,null,null);
      this.attendanceService.saveAttendanceDetails(attendanceDTO).subscribe(
          (res) => {
              if (res === true) {
                  swal.fire('Success', 'Details submitted successfully', 'success');
                  this.getAllAttendances();
              } else {
                  swal.fire('Sorry!', 'Something went wrong...!', 'error');
              }
          }
      )
    }

    viewStudent(id: number) {
        this.router.navigate(['/view-attendance/', id , 'view']);
    }

    updateStudent(id: number) {
        this.router.navigate(['/view-attendance/', id , 'update']);
    }


    onSubmitSearch() {
        const attendanceDTO = new AttendanceModel(
            0,
            null,
            null,
            null,
            this.studentForm.value.studentId,
            this.studentForm.value.classType,
            this.studentForm.value.nic,
            null
        );
        this.attendanceService.searchAttedndace(attendanceDTO).subscribe(
            (res: AttendanceSearchModel[]) => {
                this.attendanceSerchModel = res;
                if (this.attendanceSerchModel.length === 0) {
                    swal.fire('Error!', 'No such value present...!', 'error');
                    this.getAllAttendances();
                }
            }
        )
    }
}
