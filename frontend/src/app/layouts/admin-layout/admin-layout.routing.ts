import { Routes } from '@angular/router';
import { ForgotPasswordComponent } from 'app/components/forgot-password/forgot-password.component';
import { ManageOrdersComponent } from 'app/components/manage-orders/manage-orders.component';
import { NewLecturerComponent } from 'app/components/new-lecturer/new-lecturer.component';
import { ViewLecturerComponent } from 'app/components/new-lecturer/view-lecturer/view-lecturer.component';
import { NewStudentComponent } from 'app/components/new-student/new-student.component';
import { PaymentsComponent } from 'app/components/payments/payments.component';
import { ViewPaymentComponent } from 'app/components/payments/view-payment/view-payment.component';
import { SearchStudentComponent } from 'app/components/search-student/search-student.component';
import { AttendanceDetailsComponent } from 'app/components/view-attendance/attendance-details/attendance-details.component';
import { ViewAttendanceComponent } from 'app/components/view-attendance/view-attendance.component';
import { ViewOrdersComponent } from 'app/components/view-orders/view-orders.component';
import {ViewEmployeeAttendanceComponent} from '../../components/view-employee-attendance/view-employee-attendance.component';
import {UpdateEmployeeAttendanceComponent} from '../../components/view-employee-attendance/update-employee-attendance/update-employee-attendance.component';
import {ReportsComponent} from '../../components/reports/reports.component';
import { ViewStudentComponent } from 'app/components/view-student/view-student.component';
import { DashboardComponent } from 'app/dashboard/dashboard.component';
import { UpdateUsersComponent } from 'app/user-profile/update-users/update-users.component';
import { UserProfileComponent } from 'app/user-profile/user-profile.component';
import { ViewUsersComponent } from 'app/user-profile/view-users/view-users.component';
import {ManageItemComponent} from '../../components/manage-item/manage-item.component';
import {ManageSectionComponent} from '../../components/manage-section/manage-section.component';
import {ManageSubjectComponent} from '../../components/manage-subject/manage-subject.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'view-user',   component: ViewUsersComponent },
    { path: 'update-user/:id/:type',   component: UpdateUsersComponent },
    { path: 'student',   component: SearchStudentComponent },
    { path: 'forgot-password',   component: ForgotPasswordComponent },
    { path: 'view-student/:id/:type',   component: ViewStudentComponent },
    { path: 'new-student',   component: NewStudentComponent },
    { path: 'new-lecture',   component: NewLecturerComponent },
    { path: 'manage-orders',   component: ManageOrdersComponent },
    { path: 'attendance',   component: ViewAttendanceComponent },
    { path: 'manage-item',  component: ManageItemComponent },
    { path: 'manage-section',  component: ManageSectionComponent },
    { path: 'employee-attendance',   component: ViewEmployeeAttendanceComponent },
    { path: 'payment',   component: PaymentsComponent },
    { path: 'view-attendance/:id/:type',   component: AttendanceDetailsComponent },
    { path: 'view-employee-attendance/:id/:type',   component: UpdateEmployeeAttendanceComponent },
    { path: 'view-payment/:id/:type',   component: ViewPaymentComponent },
    { path: 'view-lecturer/:id/:type',   component: ViewLecturerComponent },
    {path: 'view-orders', component: ViewOrdersComponent},
    {path: 'manage-subject', component: ManageSubjectComponent},
    {path: 'view-orders', component: ViewOrdersComponent},
    {path: 'reports', component: ReportsComponent},
];
