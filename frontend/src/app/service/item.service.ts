import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from 'app/model/item.model';
import { Observable } from 'rxjs';


const URL = '/item';

@Injectable()

export class ItemService {

public BASE_URL = 'http://localhost:2244/api';

  constructor(private http: HttpClient) { }

  getAllItems(): Observable<Array<Item>> {
    return this.http.get<Array<Item>>(this.BASE_URL + URL);
  }

  deleteItem(id: number): Observable<boolean> {
    return this.http.get<boolean>(this.BASE_URL + URL + '/' + id);
  }

  saveItem(item: Item): Observable<boolean> {
    return this.http.post<boolean>(this.BASE_URL + URL, item);
  }

  getTotalItems(): Observable<number> {
    return this.http.get<number>(this.BASE_URL + URL + '/count');
  }

}
