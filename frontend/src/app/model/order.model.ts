
import { OrderDetail } from "./orderDetail.model";
import { Student } from "./students.model";

export class OrderDTO {
  oid:String;
  orderDate:String;
  studentDTO:Student;
  orderDetailDTOs:Array<OrderDetail>=[];

  constructor(oid: String, orderDate: String, studentDTO: Student, orderDetailDTOs: Array<OrderDetail>) {
    this.oid = oid;
    this.orderDate = orderDate;
    this.studentDTO = studentDTO;
    this.orderDetailDTOs = orderDetailDTOs;
  }
}
