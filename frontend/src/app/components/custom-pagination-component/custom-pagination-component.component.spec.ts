import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomPaginationComponentComponent } from './custom-pagination-component.component';

describe('CustomPaginationComponentComponent', () => {
  let component: CustomPaginationComponentComponent;
  let fixture: ComponentFixture<CustomPaginationComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomPaginationComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomPaginationComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
