import { Item } from "./item.model";
import { ItemDTO } from "./itemDto.model";


export class OrderDetail {
  qty:number;
  totalPrice:number;
  itemDTO: ItemDTO;

  constructor(qty: number, totalPrice: number, itemDTO: ItemDTO) {
    this.qty = qty;
    this.totalPrice = totalPrice;
    this.itemDTO = itemDTO;
  }
}
