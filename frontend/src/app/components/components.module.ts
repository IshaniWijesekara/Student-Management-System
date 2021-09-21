import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SearchStudentComponent } from './search-student/search-student.component';
import {MatRippleModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { NewStudentComponent } from './new-student/new-student.component';
import { ViewStudentComponent } from './view-student/view-student.component';
import { ViewAttendanceComponent } from './view-attendance/view-attendance.component';
import { AttendanceDetailsComponent } from './view-attendance/attendance-details/attendance-details.component';
import { PaymentsComponent } from './payments/payments.component';
import {ViewPaymentComponent} from './payments/view-payment/view-payment.component';
import {LoginComponent} from './login/login.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {CustomPaginationComponentComponent} from './custom-pagination-component/custom-pagination-component.component';
import { NewLecturerComponent } from './new-lecturer/new-lecturer.component';
import { ViewLecturerComponent } from './new-lecturer/view-lecturer/view-lecturer.component';
import { ManageOrdersComponent } from './manage-orders/manage-orders.component';
import { ViewOrdersComponent } from './view-orders/view-orders.component';
import { ViewEmployeeAttendanceComponent } from './view-employee-attendance/view-employee-attendance.component';
import { UpdateEmployeeAttendanceComponent } from './view-employee-attendance/update-employee-attendance/update-employee-attendance.component';
import { ReportsComponent } from './reports/reports.component';
import { ManageItemComponent } from './manage-item/manage-item.component';
import { ManageSectionComponent } from './manage-section/manage-section.component';
import { ManageSubjectComponent } from './manage-subject/manage-subject.component';
import { TestComponent } from './test/test.component';


@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatRippleModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatTooltipModule,

    ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    SearchStudentComponent,
    NewStudentComponent,
    ViewStudentComponent,
    ViewAttendanceComponent,
    AttendanceDetailsComponent,
    ManageOrdersComponent,
    ViewOrdersComponent,
    AttendanceDetailsComponent,
    PaymentsComponent,
      ViewPaymentComponent,
      LoginComponent,
      ForgotPasswordComponent,
      CustomPaginationComponentComponent,
      NewLecturerComponent,
      ViewLecturerComponent,
      ManageItemComponent,
      ManageSectionComponent,
      ManageSubjectComponent,

      ViewLecturerComponent,
      ViewEmployeeAttendanceComponent,
      UpdateEmployeeAttendanceComponent,
      ReportsComponent,
      TestComponent
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent
  ]
})
export class ComponentsModule { }
