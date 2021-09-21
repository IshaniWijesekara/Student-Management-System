import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateEmployeeAttendanceComponent } from './update-employee-attendance.component';

describe('UpdateEmployeeAttendanceComponent', () => {
  let component: UpdateEmployeeAttendanceComponent;
  let fixture: ComponentFixture<UpdateEmployeeAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateEmployeeAttendanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateEmployeeAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
