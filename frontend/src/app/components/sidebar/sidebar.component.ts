import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../service/authentication.service';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: '/view-user', title: 'User Profile',  icon:'person', class: '' },
    { path: '/student', title: 'Student Profile',  icon:'person', class: '' },
    { path: '/new-student', title: 'New Student',  icon:'person', class: '' },
    { path: '/new-lecture', title: 'New Lecturer',  icon:'person', class: '' },
    { path: '/attendance', title: 'Attendance Management',  icon:'bubble_chart', class: '' },
    { path: '/employee-attendance', title: 'Employee Attendance',  icon:'bubble_chart', class: '' },
    { path: '/manage-orders', title: 'Manage Orders',  icon:'content_paste', class: '' },
    { path: '/payment', title: 'Payment Management',  icon:'content_paste', class: '' },
    { path: '/reports', title: 'Reports',  icon:'person', class: '' },
    { path: '/manage-item', title: 'Manage Item',  icon: 'person', class: '' },
    { path: '/manage-section', title: 'Manage Section',  icon: 'person', class: '' },
    { path: '/manage-subject', title: 'Manage Subject',  icon: 'person', class: '' }
];

export const ROUTES2: RouteInfo[] = [

    { path: '/dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: '/view-user', title: 'User Profile',  icon: 'person', class: '' },
    { path: '/student', title: 'Student Profile',  icon: 'person', class: '' },
    // { path: '/new-student', title: 'New Student',  icon:'person', class: '' },
    { path: '/new-lecture', title: 'New Lecturer',  icon: 'person', class: '' },
    { path: '/manage-orders', title: 'Manage Orders',  icon:'person', class: '' },
    { path: '/attendance', title: 'Attendance Management',  icon: 'bubble_chart', class: '' },
    { path: '/employee-attendance', title: 'Employee Attendance',  icon:'bubble_chart', class: '' },
    // { path: '/table-list', title: 'Table List',  icon:'content_paste', class: '' },
    { path: '/payment', title: 'Payment Management',  icon:'content_paste', class: '' },
    { path: '/manage-item', title: 'Manage Item',  icon: 'person', class: '' },
    // { path: '/manage-section', title: 'Manage Section',  icon: 'person', class: '' },
    { path: '/reports', title: 'Reports',  icon:'person', class: '' },


];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  currentUser = this.authenticationService.currentUserValue.userType

    constructor(private authenticationService: AuthenticationService) {}



ngOnInit() {

    // tslint:disable-next-line:triple-equals
    if (this.currentUser == 'ADMIN') {
        this.menuItems = ROUTES2.filter(menuItem => menuItem);
        // tslint:disable-next-line:triple-equals
    } else if (this.currentUser == 'FRONT') {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }


  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
}
