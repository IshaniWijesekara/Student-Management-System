import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LecturerModel} from '../../../model/lecturer.model';
import {SubjectModel} from '../../../model/subject.model';
import {PatternValidation} from '../../../enum/pattern-validation.enum';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {LecturerService} from '../../../service/lecturer.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-view-lecturer',
  templateUrl: './view-lecturer.component.html',
  styleUrls: ['./view-lecturer.component.css']
})
export class ViewLecturerComponent implements OnInit {
    public lectureForm: FormGroup;
    public lecturerModel: LecturerModel;
    public subjectModel: SubjectModel[] = [];
    public id: number;
    public type: string;

  constructor( private route: ActivatedRoute,
               private lectureService: LecturerService,
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
      this.route.params.subscribe(
          (param: Params) => {
              this.id = param.id;
              this.type = param.type;
          }
      );
      this.getLectureDetails();
  }

    get FormControls() {
        return this.lectureForm.controls;
    }

    getLectureDetails() {
      this.lectureService.getDetailsById(this.id).subscribe(
          (res: LecturerModel) => {
              this.lecturerModel = res;
          }
      );
    }

    updateDetails() {
        let isValid = true;
        let errorMessage = '';

        if (!this.lectureForm.valid) {
            isValid = false;
            errorMessage = 'Please, Fill the form Data...!'
        }

        if (isValid) {
            this.lectureService.updateLecture(this.lecturerModel).subscribe(
                (res: boolean) => {
                    if (res === true) {
                        swal.fire('Success', 'Details submitted successfully', 'success');
                    } else {
                        swal.fire('Sorry!', 'Something went wrong...!', 'error');
                    }
                }
            )
        }
    }

    back() {
        this.router.navigate(['/new-lecture/']);
    }

    cancel() {
        this.router.navigate(['/new-lecture/']);
    }


}
