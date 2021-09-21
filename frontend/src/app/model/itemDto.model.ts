export class ItemDTO {
  itemCode: number;
  description: string;
  qty: number;
  unitPrice:number;

  constructor(itemCode: number, description: string, qty: number, unitPrice: number) {
    this.itemCode = itemCode;
    this.description = description;
    this.qty = qty;
    this.unitPrice = unitPrice;
  }
}
