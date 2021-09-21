import { Component, OnInit } from '@angular/core';
import {StudentService} from '../../service/student.service';
import {StudentDTO} from '../../model/student.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {PatternValidation} from '../../enum/pattern-validation.enum';
import swal from 'sweetalert2';

@Component({
  selector: 'app-search-student',
  templateUrl: './search-student.component.html',
  styleUrls: ['./search-student.component.css']
})
export class SearchStudentComponent implements OnInit {
  public studentModel: StudentDTO[] = [];
  public studentDetails: StudentDTO;
  public studentForm: FormGroup;

  constructor(private studentService: StudentService,
              public modalService: NgbModal,
              private router: Router
              ) { }

  ngOnInit(): void {
      this.studentForm = new FormGroup({
          studentId: new FormControl(null),
          name: new FormControl(null,[Validators.required , Validators.pattern(PatternValidation.nameValidation)]),
          nic: new FormControl(null,[Validators.required , Validators.pattern(PatternValidation.nicValidation)]),
      });
    this.getAllStudents();
  }

  getAllStudents() {
    this.studentService.getAllStudents().subscribe(
        (res: StudentDTO[]) => {
          this.studentModel = res;
        }
    )
  }

  onAdd() {
      this.router.navigate(['/new-student/']);
  }

  onSubmitSearch() {
      const studentDTO = new StudentDTO(
          this.studentForm.value.studentId,
          this.studentForm.value.name,
          this.studentForm.value.name,
          this.studentForm.value.name,
          null,
          null,
          null,
          this.studentForm.value.nic,
          null,
          null,
          0,
          null,
          null,
      );
      this.loadSearchResultList(studentDTO);
  }

    loadSearchResultList(studentDTO: StudentDTO): void {
        this.studentService.searchStudent(studentDTO).subscribe(
            (data: StudentDTO[]) => {
                this.studentModel = data;
            },
            (error) => {
                // swal.fire('Sorry!', 'Something went wrong...!', 'error');
            },
            () => {
                if (this.studentModel == null || this.studentModel.length === 0) {
                    swal.fire('Sorry', 'No result found.', 'error');
                    this.getAllStudents()
                }
            }
        );
    }

    viewStudent(id: number) {
        this.router.navigate(['/view-student/', id , 'view']);
    }

    updateStudent(id: number) {
        this.router.navigate(['/view-student/', id , 'update']);
    }

    delete(id: number){
      this.studentService.delete(id).subscribe(
          (res: boolean)=> {
              if(res === true) {
                  swal.fire('Success', 'Deleted Successfully.', 'success');
                  this.studentModel.splice(id,1);
                  this.getAllStudents();
              }else {
                  swal.fire('Sorry', 'Something went wrong.', 'error');
              }
          }
      )
    }

}
