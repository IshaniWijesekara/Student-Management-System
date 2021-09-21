import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ClassModel} from '../../model/class.model';
import {AttendaneService} from '../../service/attendane.service';
import {PatternValidation} from '../../enum/pattern-validation.enum';
import {Daily} from '../../model/dailyOrder.model';
import {OrderService} from '../../service/orders.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
    public studentForm: FormGroup;
    public classModel: ClassModel[] = [];
    orders: Array<Daily> = [];
  constructor(private attendanceService: AttendaneService, private orderService: OrderService ) { }

  ngOnInit(): void {
      this.studentForm = new FormGroup({
          fromDate: new FormControl(null),
          fromDate1: new FormControl(null),
          fromDate3: new FormControl(null),
          fromDate4: new FormControl(null),
          toDate: new FormControl(null),
          toDate1: new FormControl(null),
          toDate3: new FormControl(null),
          toDate4: new FormControl(null),
          inTime: new FormControl(null),
          studentId: new FormControl(null),
          classType: new FormControl(null),
          orderDate: new FormControl(null),
          orderDateSec: new FormControl(null),
          orderDateStudent: new FormControl(null)
      });
    this.getAllClasses();
  }

    onSubmitSearch(date: string) {
        this.orderService.getDailyOrdersByDate(date);
    }


    loadAllStudents(date: string): void {
        this.orderService.getDailyOrdersByDate(date).subscribe(
            (result) => {
                this.orders = result;
            }
        )
    }
    getAllClasses() {
        this.attendanceService.getAllClasses().subscribe(
            (data: ClassModel[]) => {
                this.classModel = data;
            }
        )
    }

    search1() {
      this.attendanceService.printAttendanceReportByMonth(this.studentForm.value.classType,
          this.studentForm.value.fromDate, this.studentForm.value.toDate).subscribe(
          (data: any) => {
              const file = new Blob([data], { type: 'application/pdf' });
              const fileURL = URL.createObjectURL(file);
              window.open(fileURL);
          },
      )
    }

    search2() {
        this.attendanceService.printAttendanceReportByStudent(this.studentForm.value.classType, this.studentForm.value.studentId,
            this.studentForm.value.fromDate1, this.studentForm.value.toDate1).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }

    search3() {
        this.attendanceService.printAttendanceReportLate(this.studentForm.value.classType, this.studentForm.value.inTime,
            this.studentForm.value.fromDate3, this.studentForm.value.toDate3).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }

    search4() {
        this.attendanceService.printEmployeeAttendance(
            this.studentForm.value.fromDate4, this.studentForm.value.toDate4).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }

    search5() {
        this.attendanceService.printPaymentReport(
            this.studentForm.value.classType).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }

    printDailyOrders() {
        this.orderService.printDailyOrders(
            this.studentForm.value.orderDate).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }

    printDailyProfit() {
        this.orderService.printDailyProfit(
            this.studentForm.value.orderDateSec).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }

    printDailyStudentsOrders() {
        this.orderService.printDailyStudentsOrders(
            this.studentForm.value.orderDateStudent).subscribe(
            (data: any) => {
                const file = new Blob([data], { type: 'application/pdf' });
                const fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            },
        )
    }




}

