import { Injectable } from '@angular/core';
import {Product} from "@app/_models/product";
import {HttpClient} from "@angular/common/http";
import {User} from "@app/_models/user";
import {environment} from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient) { }

  find(){
    return this.http.get<Product[]>(`${environment.apiUrl}/api/product`);
  }
}
