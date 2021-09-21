import {Component, OnInit, ViewChild} from '@angular/core';
import {Item} from '../../model/item.model';
import {ItemService} from '../../service/item.service';
import {NgForm} from '@angular/forms';
import swal from 'sweetalert2';


@Component({
  selector: 'app-manage-item',
  templateUrl: './manage-item.component.html',
  styleUrls: ['./manage-item.component.css']
})
export class ManageItemComponent implements OnInit {

  items: Array<Item> = [];

  selectedItem: Item = new Item();
  tempItem: Item = null;
  manuallySelected = false;
  @ViewChild('frmItems') frmItems: NgForm;

  constructor(private itemService: ItemService) { }

  ngOnInit() {
    this.loadAllItems();
  }

  loadAllItems(): void {
    this.itemService.getAllItems().subscribe(
        (result) => {
          this.items = result;
        }
    )
  }

  deleteItem(id: number): void {
    if (confirm('Are you sure you want to delete this item?')) {
      this.itemService.deleteItem(id).subscribe(
          (result) => {
            if (result) {
              swal.fire('Success', 'Item has been deleted successfully', 'success');
            } else {
              swal.fire('Sorry!', 'Failed to delete the item', 'error');
            }
            this.loadAllItems();
          }
      )
    }
  }

  selectItem(item: Item): void {
    this.clear();
    this.selectedItem = item;
    this.tempItem = Object.assign({}, item);
    this.manuallySelected = true;
  }

  clear(): void {
    const index = this.items.indexOf(this.selectedItem);
    if (index !== -1) {
      this.items[index] = this.tempItem;
      this.tempItem = null;
    }
    this.selectedItem = new Item();
    this.manuallySelected = false;
  }

  saveItem(): void {
    this.itemService.saveItem(this.selectedItem).subscribe(
        (result) => {
          if (result) {
            swal.fire('Success', 'Details submitted successfully', 'success');
            this.loadAllItems();
          } else {
            swal.fire('Sorry!', 'Something went wrong...!', 'error');
          }
        }
    )
  }
}
