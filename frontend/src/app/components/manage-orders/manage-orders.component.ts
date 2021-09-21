import { Component, OnInit } from '@angular/core';
import { Item } from 'app/model/item.model';
import { ItemDTO } from 'app/model/itemDto.model';
import { OrderDTO } from 'app/model/order.model';
import { OrderDetail } from 'app/model/orderDetail.model';
import { Student } from 'app/model/students.model';
import { ItemService } from 'app/service/item.service';
import { OrderService } from 'app/service/orders.service';
import { StudentService } from 'app/service/student.service';
import swal from 'sweetalert2';
import {Router} from '@angular/router';


@Component({
  selector: 'app-manage-orders',
  templateUrl: './manage-orders.component.html',
  styleUrls: ['./manage-orders.component.css']
})
export class ManageOrdersComponent implements OnInit {


  students: Array<Student> = [];
  items: Array<Item> = [];
  selectedStudent: Student = new Student();
  selectedItem: Item = new Item();
  total1 = 0;
  orderDetails: Array<OrderDetail> = [];
  orderDetail: OrderDetail;
  today = Date.now();
  studentss: any;


  constructor(private studentService: StudentService, private itemService: ItemService,
              private orderService: OrderService, private router: Router) {}
  ngOnInit() {
    this.loadStudentIds();
    this.loadItemIds();
  }

  loadStudentIds(): void {
    this.studentService.getAllStudents().subscribe(result => {
      this.studentss = result;
    })
  }

  loadItemIds(): void {
    this.itemService.getAllItems().subscribe(result => {
      this.items = result;
    });
  }

  removeItem(item) {
    const index = this.orderDetails.indexOf(item);
    this.orderDetails.splice(index, 1);
  }


  addItems(qty): void {
    const total = qty * this.selectedItem.unitPrice;
    const remainingQTY = this.selectedItem.qty - qty;
    this.orderDetail = new OrderDetail(qty, total, new ItemDTO(this.selectedItem.itemCode,
        this.selectedItem.description, remainingQTY, this.selectedItem.unitPrice));
    this.orderDetails.push(this.orderDetail);
    document.getElementById('finalTotal').setAttribute('value', total.toString());
  }

  placeOrder(orderDate, orderId) {
    const orderDTO: OrderDTO = new OrderDTO(orderId, orderDate, this.selectedStudent, this.orderDetails);
    this.orderService.saveOrder(orderDTO).subscribe(result => {
      if (result) {
        swal.fire('Success', 'Order has been saved successfully', 'success');
          if (this.selectedStudent.studentId != null) {
              this.router.navigate(['/payment']);
          }
      } else {
        swal.fire('Sorry!', 'Failed to save the order', 'error');
      }
    });
  }




}
