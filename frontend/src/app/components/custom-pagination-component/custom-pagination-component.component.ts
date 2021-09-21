import {ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-custom-pagination-component',
  templateUrl: './custom-pagination-component.component.html',
  styleUrls: ['./custom-pagination-component.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class CustomPaginationComponentComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  @Input() id: string;
  @Input() maxSize: number=10;
  @Input() rowcount: number;
  @Input()
  get directionLinks(): boolean {
    return this._directionLinks;
  }
  set directionLinks(value: boolean) {
    this._directionLinks = !!value && <any>value !== 'false';
  }
  @Input()
  get autoHide(): boolean {
    return this._autoHide;
  }
  set autoHide(value: boolean) {
    this._autoHide = !!value && <any>value !== 'false';
  }
  @Input() previousLabel: string = 'Previous';
  @Input() nextLabel: string = 'Next';
  @Input() screenReaderPaginationLabel: string = 'Pagination';
  @Input() screenReaderPageLabel: string = 'page';
  @Input() screenReaderCurrentLabel: string = `You're on page`;
  @Output() pageChange: EventEmitter<number> = new EventEmitter<number>();

  private _directionLinks: boolean = true;
  private _autoHide: boolean = false;
  public totalListCount:Number=this.maxSize;

}
