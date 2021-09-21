import { Component, OnInit } from '@angular/core';
import { Daily } from 'app/model/dailyOrder.model';
import { OrderService } from 'app/service/orders.service';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css']
})
export class ViewOrdersComponent implements OnInit {

  orders: Array<Daily> = [];

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
  }


  loadAllStudents(date:string): void {
    this.orderService.getDailyOrdersByDate(date).subscribe(
      (result) => {
        this.orders = result;
      }
    )
  }

  onSubmitSearch(date:string) {
    this.orderService.getDailyOrdersByDate(date);
}


}
