import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import swal from 'sweetalert2';
import {SubjectModel} from '../../model/subject.model';
import {SubjectService} from '../../service/subject.service';


@Component({
  selector: 'app-manage-subject',
  templateUrl: './manage-subject.component.html',
  styleUrls: ['./manage-subject.component.css']
})
export class ManageSubjectComponent implements OnInit {


  subjects: Array<SubjectModel> = [];

  selectedSubject: SubjectModel = new SubjectModel();
  tempSubject: SubjectModel = null;
  manuallySelected = false;
  @ViewChild('frmSubjects') frmSubjects: NgForm;

  constructor(private subjectService: SubjectService) { }

  ngOnInit() {
    this.loadAllSubjects();
  }

  loadAllSubjects(): void {
    this.subjectService.getAllSubjects().subscribe(
        (result) => {
          this.subjects = result;
        }
    )
  }

  deleteSubjects(subject: SubjectModel): void {
    if (confirm('Are you sure you want to delete this subject?')) {
      this.subjectService.deleteSubject(subject.id).subscribe(
          (result) => {
            if (result) {
              swal.fire('Success', 'Subject has been deleted successfully', 'success');
            } else {
              swal.fire('Sorry!', 'Failed to delete the Subject', 'error');
            }
            this.loadAllSubjects();
          }
      )
    }
  }

  selectSubjects(subjectModel: SubjectModel): void {
    this.clear();
    this.selectedSubject = subjectModel;
    this.tempSubject = Object.assign({}, subjectModel);
    this.manuallySelected = true;
  }

  clear(): void {
    const index = this.subjects.indexOf(this.selectedSubject);
    if (index !== -1) {
      this.subjects[index] = this.tempSubject;
      this.tempSubject = null;
    }
    this.selectedSubject = new SubjectModel();
    this.manuallySelected = false;
  }

  saveSubject(): void {
    this.subjectService.saveSubject(this.selectedSubject).subscribe(
        (result) => {
          if (result) {
            swal.fire('Success', 'Details submitted successfully', 'success');
            this.loadAllSubjects();
          } else {
            swal.fire('Sorry!', 'Something went wrong...!', 'error');
          }
        }
    )
  }
}
