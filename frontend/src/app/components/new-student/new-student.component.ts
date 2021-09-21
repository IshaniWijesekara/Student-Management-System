import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, NgForm} from '@angular/forms';
import {Classes} from 'app/model/classes.model';
import {lecture} from 'app/model/lecture.model';
import {Section} from 'app/model/section.model';
import {Student} from 'app/model/students.model';
import {ClassesService} from 'app/service/classes.service';
import {LectureService} from 'app/service/lecture.service';
import {SectionService} from 'app/service/section.service';
import {StudentService} from 'app/service/student.service';
import swal from 'sweetalert2';
import {Router} from '@angular/router';


@Component({
    selector: 'app-new-student',
    templateUrl: './new-student.component.html',
    styleUrls: ['./new-student.component.css']
})
export class NewStudentComponent implements OnInit {
    classDisable: boolean;
    sectionDisable: boolean;
    lecDisable: boolean;
    students: Array<Student> = [];
    selectedStudent: Student = new Student();
    tempStudent: Student = null;
    manuallySelected = false;
    @ViewChild('frmStudents') frmStudents: NgForm;
    lectureList: Array<lecture> = [];
    classList: Array<Classes> = [];
    sectionList: Array<Section> = [];

    // tslint:disable-next-line:max-line-length
    constructor(private studentService: StudentService, private lectureService: LectureService,
                private classService: ClassesService, private sectionService: SectionService, private router: Router) {
    }

    ngOnInit() {
        this.loadAllStudents();
        this.loadLectures();
        this.loadClasses();
        this.loadSections();
    }

    clear(): void {
        const index = this.students.indexOf(this.selectedStudent);
        if (index !== -1) {
            this.students[index] = this.tempStudent;
            this.tempStudent = null;
        }
        this.selectedStudent = new Student();
        this.manuallySelected = false;
    }


    loadAllStudents(): void {
        this.studentService.getAllStudentsTable().subscribe(
            (result) => {
                this.students = result;
            }
        )
    }


    loadSections() {
        this.sectionService.searchSection()
            .subscribe(result => {
                this.sectionList = result;
            });
    }


    loadLectures() {
        this.lectureService.searchLectures()
            .subscribe(result => {
                this.lectureList = result;
            });
    }


    loadClasses() {
        this.classService.searchClasses()
            .subscribe(result => {
                this.classList = result;
            });
    }


    selectStudent(student: Student): void {
        this.clear();
        this.selectedStudent = student;
        this.tempStudent = Object.assign({}, student);
        this.manuallySelected = true;
        this.classDisable = false;
        this.sectionDisable = false;
        this.lecDisable = false;
    }

    saveStudent(): void {
        this.studentService.saveStudentDetails(this.selectedStudent).subscribe(
            (result) => {
                if (result) {

                    swal.fire('Sorry!', 'Something went wrong...!', 'error');

                } else {
                    this.loadAllStudents();
                    swal.fire('Success', 'Details submitted successfully', 'success');
                    if (this.selectedStudent.studentId != null) {
                        this.router.navigate(['/payment']);
                    }


                }
            }
        )
    }

}
