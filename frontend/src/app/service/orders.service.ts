import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Daily } from 'app/model/dailyOrder.model';
import { OrderDTO } from 'app/model/order.model';
import { Observable } from 'rxjs';

const URL = '/orders';

@Injectable()
export class OrderService {

public BASE_URL = 'http://localhost:2244/api';

  constructor(private http: HttpClient) { }

  saveOrder(order: OrderDTO): Observable<boolean> {
    return this.http.post<boolean>(this.BASE_URL + URL + '/saveOrders', order);
  }

  getTotalOrders(): Observable<number> {
    return this.http.get<number>(this.BASE_URL + URL + '/count');
  }

getDailyOrdersByDate(date: string): Observable<Array<Daily>> {
  return this.http.get<Array<Daily>>(this.BASE_URL + URL + '/getDailyOrders' + date);
}

  printDailyOrders(date: string) {
    return this.http.get(this.BASE_URL + URL + '/getDailyOrders/' + date, {responseType: 'arraybuffer'});
  }

  printDailyProfit(date: string) {
    return this.http.get(this.BASE_URL + URL + '/getDailyProfit/' + date, {responseType: 'arraybuffer'});
  }

  printDailyStudentsOrders(date: string) {
    return this.http.get(this.BASE_URL + URL + '/getDailyOrdersStudents/' + date, {responseType: 'arraybuffer'});
  }


}
