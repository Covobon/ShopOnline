import { Injectable } from '@angular/core';
import {Product} from '@app/_models/product';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient) { }

  find(url) {
    return this.http.get<Product[]>(url);
  }
  post(url: string, product: Product) {
    return this.http.post(url, product);
  }
  put(url: string, product: Product) {
    return this.http.post(url, product);
  }

}
