import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {PaymentModel} from '../model/payment.model';

@Injectable({
    providedIn: 'root'
})
export class PaymentService {
    private headers;
    public BASE_URL = 'http://localhost:2244/';
    private headersJson = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    /** Save Payment Details **/
    savePayments(paymentDTO: PaymentModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/payment/savePayment', paymentDTO, { headers: this.headersJson });
    }

    /** Update Payment Details **/
    updatePayments(paymentDTO: PaymentModel): Observable<Object> {
        return this.httpClient.post(this.BASE_URL + 'api/payment/updatePayment', paymentDTO, { headers: this.headersJson });
    }

    /** Get All Payments Details **/
    getAllPayments():  Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/payment/',  {headers: this.headers});
    }

    /** Search Payments Details By Student ID **/
    getPaymentsByStudentId(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/payment/getPaymentById/' + id, {headers: this.headers});
    }

    /** Search Payments Details By Payment ID **/
    getPaymentsById(id: number): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/payment/getPaymentDetails/' + id, {headers: this.headers});
    }

    /** Search Payments Details By Month **/
    getPaymentsByMonth(month: string): Observable<Object> {
        return this.httpClient.get(this.BASE_URL + 'api/payment/getPayment/' + month, {headers: this.headers});
    }

}
