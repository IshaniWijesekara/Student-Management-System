import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewEmployeeAttendanceComponent } from './view-employee-attendance.component';

describe('ViewEmployeeAttendanceComponent', () => {
  let component: ViewEmployeeAttendanceComponent;
  let fixture: ComponentFixture<ViewEmployeeAttendanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewEmployeeAttendanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewEmployeeAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
