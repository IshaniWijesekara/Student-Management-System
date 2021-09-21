import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {StudentService} from '../../service/student.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {StudentDTO} from '../../model/student.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-view-student',
  templateUrl: './view-student.component.html',
  styleUrls: ['./view-student.component.css']
})
export class ViewStudentComponent implements OnInit {
  public studentForm: FormGroup;
  public id: number;
  public type: string;
  public studentModel: StudentDTO;

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
      this.route.params.subscribe(
          (param: Params) => {
              this.id = param.id;
              this.type = param.type;
          }
      );
      this.studentForm = new FormGroup({
          regNo: new FormControl(null),
          regDate: new FormControl(null),
          firstName: new FormControl(null),
          middleName: new FormControl(null),
          lastName: new FormControl(null),
          nic: new FormControl(null),
          schoolName: new FormControl(null),
          address: new FormControl(null),
          guradianName: new FormControl(null),
          guardianContactNo: new FormControl(null),
          email: new FormControl(null),
          birthday: new FormControl(null),
      });
      this.getStudentDetails();
  }

  getStudentDetails() {
    this.studentService.getStudentById(this.id).subscribe(
        (res: StudentDTO) => {
          this.studentModel = res;
        }
    )
  }

    update(id: number) {
      this.studentModel.studentId = id;
        this.studentService.updateDetails(this.studentModel).subscribe(
            (res: boolean) => {
                if (res === true) {
                    swal.fire('Success', 'Details submitted successfully', 'success');
                    this.router.navigate(['/student']);
                } else {
                    swal.fire('Sorry!', 'Something went wrong...!', 'error');
                }
            }
        )
    }

    cancel() {
        this.router.navigate(['/student']);
    }

}
