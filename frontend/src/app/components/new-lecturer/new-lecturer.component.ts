import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LecturerService} from '../../service/lecturer.service';
import {LecturerModel} from '../../model/lecturer.model';
import swal from 'sweetalert2';
import {SubjectModel} from '../../model/subject.model';
import {PatternValidation} from '../../enum/pattern-validation.enum';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-lecturer',
  templateUrl: './new-lecturer.component.html',
  styleUrls: ['./new-lecturer.component.css']
})
export class NewLecturerComponent implements OnInit {
  public lectureForm: FormGroup;
  public lecturerModel: LecturerModel[] = [];
  public subjectModel: SubjectModel[] = [];
  constructor(private lecturerService: LecturerService,
              private router: Router) { }

  ngOnInit(): void {
      this.lectureForm = new FormGroup({
          name: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.nameValidation)]),
          email: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.emailValidation)]),
          nic: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.nicValidation)]),
          address: new FormControl(null),
          contactNo: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.contactNumberValidation)]),
          subject: new FormControl(null),
      });
      this.getAllLecturers();
      this.getAllSubjects();
  }

  getAllSubjects() {
      this.lecturerService.getAllSubjects().subscribe(
          (res: SubjectModel[]) => {
              this.subjectModel = res;
          }
      )
  }

  getAllLecturers() {
    this.lecturerService.getAllLecturer().subscribe(
        (res: LecturerModel[]) => {
          this.lecturerModel = res;
        }
    )
  }

  addDetails() {
      let isValid = true;
      let errorMessage = '';

      if (!this.lectureForm.valid) {
          isValid = false;
          errorMessage = 'Please, Fill the form Data...!'
      }

      if (isValid) {
          const lectureDTO = new LecturerModel(0, this.lectureForm.value.name, this.lectureForm.value.contactNo, this.lectureForm.value.email,
              this.lectureForm.value.nic, this.lectureForm.value.address, this.lectureForm.value.subject);
          this.lecturerService.saveLecture(lectureDTO).subscribe(
              (res: boolean) => {
                  if (res === true) {
                      swal.fire('Success', 'Details submitted successfully', 'success');
                      this.getAllLecturers();
                  } else {
                      swal.fire('Sorry!', 'Something went wrong...!', 'error');
                  }
              }
          )
      } else {
          swal.fire('Error!', errorMessage, 'error');
      }
  }

    get FormControls() {
        return this.lectureForm.controls;
    }

    viewLecturer(id: number) {
        this.router.navigate(['/view-lecturer/', id , 'view']);
    }

    updateLecturer(id: number) {
        this.router.navigate(['/view-lecturer/', id , 'update']);
    }
}
