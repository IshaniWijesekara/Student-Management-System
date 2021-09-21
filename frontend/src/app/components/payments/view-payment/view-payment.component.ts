import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {StudentDTO} from '../../../model/student.model';
import {PaymentModel} from '../../../model/payment.model';
import {PaymentService} from '../../../service/payment.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {StudentService} from '../../../service/student.service';
import swal from 'sweetalert2';

@Component({
    selector: 'app-view-payment',
    templateUrl: './view-payment.component.html',
    styleUrls: ['./view-payment.component.css']
})
export class ViewPaymentComponent implements OnInit{
    public studentForm: FormGroup;
    public studentModel: StudentDTO;
    public paymentModel: PaymentModel;
    public id: number;
    public type: string;

    constructor(private studentService: StudentService,
                private router: Router,
                private route: ActivatedRoute,
                private paymentService: PaymentService){}

    ngOnInit(): void {
        this.studentForm = new FormGroup({
            studentId: new FormControl(null),
            name: new FormControl(null),
            nic: new FormControl(null),
            studentId1: new FormControl(null),
            studentName1: new FormControl(null),
            studentNIC1: new FormControl(null),
            date: new FormControl(null),
            month: new FormControl(null),
            fee: new FormControl(null),
            type: new FormControl(null),
        });
        this.route.params.subscribe(
            (param: Params) => {
                this.id = param.id;
                this.type = param.type;
            }
        );
        this.getPaymentDetails();
    }

    getPaymentDetails() {
        this.paymentService.getPaymentsById(this.id).subscribe(
            (res: PaymentModel) => {
                this.paymentModel = res;
            }
        )
    }

    update() {
        this.paymentService.updatePayments(this.paymentModel).subscribe(
            (res: boolean) => {
                if(res === true) {
                    swal.fire('Success', 'Details submitted successfully', 'success');
                }else {
                    swal.fire('Sorry!', 'Something went wrong...!', 'error');
                }
            }
        )
    }

    cancel(){
        this.router.navigate(['/payment/']);
    }

    back(){
        this.router.navigate(['/payment/']);
    }

}