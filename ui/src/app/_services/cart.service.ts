import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {Product} from '@app/_models/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {}
  getAllProduct() {
    return this.http.get<Product[]>(`${environment.apiUrl}/api/cart`);
  }
  removeFormCart(product: Product) {
    return this.http.delete(`${environment.apiUrl}/api/cart${product.productId}`);
  }
  postToCart(product: Product) {
    return this.http.post(`${environment.apiUrl}/api/cart`, product);
  }
}
