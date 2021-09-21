import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {StudentDTO} from '../../model/student.model';
import {Router} from '@angular/router';
import {StudentService} from '../../service/student.service';
import {PaymentService} from '../../service/payment.service';
import {PaymentModel} from '../../model/payment.model';
import swal from 'sweetalert2';
import {PatternValidation} from '../../enum/pattern-validation.enum';
import {ClassModel} from '../../model/class.model';
import {AttendaneService} from '../../service/attendane.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {
    @Input() index: number;
    @Input() page: number=1;
    @Input() itemsPerPage: number=5;
  public studentForm: FormGroup;
  public studentModel: StudentDTO;
  public paymentModel: PaymentModel[] = [];
    public classModel: ClassModel[] = [];
  constructor( private studentService: StudentService,
              private router: Router,
               private paymentService: PaymentService,
               private attendanceService: AttendaneService,) { }

  ngOnInit(): void {
      this.studentForm = new FormGroup({
          studentId: new FormControl(null),
          name: new FormControl(null,[Validators.required , Validators.pattern(PatternValidation.nameValidation)]),
          nic: new FormControl(null, [Validators.required , Validators.pattern(PatternValidation.nicValidation)]),
          studentId1: new FormControl(null),
          studentName1: new FormControl(null),
          studentNIC1: new FormControl(null),
          date: new FormControl(null),
          month: new FormControl(null),
          fee: new FormControl(null),
          type: new FormControl(null),
          class_type: new FormControl(null),
      });
      this.getAllPayments();
      this.getAllClasses();
  }

    getAllClasses() {
        this.attendanceService.getAllClasses().subscribe(
            (data: ClassModel[]) => {
                this.classModel = data;
            }
        )
    }


    getStudentDetails() {
        this.studentService.getStudentById(this.studentForm.value.studentId1).subscribe(
            (res: StudentDTO) => {
                this.studentModel = res;
                this.studentForm.patchValue(
                    {
                        studentName1: this.studentModel.firstName + ' ' + this.studentModel.middleName + ' ' + this.studentModel.lastName,
                        studentNIC1: this.studentModel.nic,
                    }
                );
            }
        )
    }

    addPayments() {
        const paymentModel = new PaymentModel(0, this.studentForm.value.month, this.studentForm.value.type,
            this.studentForm.value.fee,this.studentForm.value.fee, this.studentForm.value.date,
            this.studentForm.value.studentId1,
            this.studentForm.value.studentName1,
            this.studentForm.value.studentNIC1, 0,this.studentForm.value.class_type
            );
        this.paymentService.savePayments(paymentModel).subscribe(
            (res: boolean) => {
                if (res === true) {
                    swal.fire('Success', 'Details submitted successfully', 'success');
                    this.getAllPayments();
                } else {
                    swal.fire('Sorry!', 'Something went wrong...!', 'error');
                }
            }
        )
    }

    getAllPayments() {
      this.paymentService.getAllPayments().subscribe(
          (res: PaymentModel[]) => {
            this.paymentModel = res;
          }
      )
    }

    updatePayment(id: number) {
        this.router.navigate(['/view-payment/', id , 'update']);
    }

    viewPayment(id: number) {
        this.router.navigate(['/view-payment/', id , 'view']);
    }
}
