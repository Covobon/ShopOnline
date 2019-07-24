import { Injectable } from '@angular/core';
import {Product} from "@app/_models/product";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private product: Product,
    private http: HttpClient) { }

  findById() {

  }
}
