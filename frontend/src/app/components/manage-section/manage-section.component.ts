import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Classes } from 'app/model/classes.model';
import { lecture } from 'app/model/lecture.model';
import { Section } from 'app/model/section.model';
import { ClassesService } from 'app/service/classes.service';
import { LectureService } from 'app/service/lecture.service';
import { SectionService } from 'app/service/section.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-manage-section',
  templateUrl: './manage-section.component.html',
  styleUrls: ['./manage-section.component.css']
})
export class ManageSectionComponent implements OnInit {


  selectedSection: Section = new Section();
  sections: Array<Section> = [];
  tempSection: Section = null;
  manuallySelected = false;
  lectureList: Array<lecture> = [];
  classList: Array<Classes> = [];
  @ViewChild('frmSections') frmItems: NgForm;



  constructor(private sectionService: SectionService, private lectureService: LectureService, private classService: ClassesService) { }

  ngOnInit(): void {
    this.loadAllSections();
    this.loadClasses();
    this.loadLectures();
  }


  saveSection(): void {
    this.sectionService.saveSection(this.selectedSection).subscribe(

        (result) => {
          if (result) {
            swal.fire('Success', 'Details submitted successfully', 'success');
            this.loadAllSections();
          } else {
            swal.fire('Sorry!', 'Something went wrong...!', 'error');
          }
        }
    )
  }

  selectSection(section: Section): void {
    this.clear();
    this.selectedSection = section;
    this.tempSection = Object.assign({}, section);
    this.manuallySelected = true;
  }

  clear(): void {
    const index = this.sections.indexOf(this.selectedSection);
    if (index !== -1) {
      this.sections[index] = this.tempSection;
      this.tempSection = null;
    }
    this.selectedSection = new Section();
    this.manuallySelected = false;
  }


  loadAllSections(): void {
    this.sectionService.getAllSections().subscribe(
        (result) => {
          this.sections = result;
        }
    )
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

    deleteSection(id: number): void {
  if (confirm('Are you sure you want to delete this Section?')) {
    this.sectionService.deleteSection(id).subscribe(
        (result) => {
          if (result) {
            swal.fire('Success', 'Section has been deleted successfully', 'success');
          } else {
            swal.fire('Sorry!', 'Failed to delete the Section', 'error');
          }
          this.loadAllSections();
        }
    )
  }
}


}
