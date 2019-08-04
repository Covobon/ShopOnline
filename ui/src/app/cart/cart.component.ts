import { Component, OnInit } from '@angular/core';
import {Product} from '@app/_models/product';
import {CartProduct} from '@app/_models/cart-product';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {ProductService} from '@app/_services/product.service';
import {CartService} from '@app/_services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private products: Product[];
  private cartProducts: CartProduct[];

  constructor(private http: HttpClient,
              private productService: ProductService,
              private cartService: CartService) { }

  ngOnInit() {
    this.productService.find(`${environment.apiUrl}/api/cart`).subscribe(data => {
      this.products = data;
    });
  }
  src(product: Product) {
    return 'http://localhost:8081/api/img/' + product.category.toLowerCase() + '/' + product.images[0].imageName;
  }

  addToCart(product: Product) {
    this.products.forEach( (value) => {
      if (product.productId === value.productId) {
        return;
      }
    });
    product.amount = 1;
    this.products[this.products.length] = product;
  }
  remove(product: Product) {
    /*for (let i = 1; i < this.products.length; i++) {
      if (this.products[i].productId === product.productId) {
        this.products[i] = null;
      }
    }*/
    this.http.delete(`${environment.apiUrl}/api/cart/${product.productId}`).subscribe(data => {
    }, error => {
      console.log(error);
      this.ngOnInit();
    });
  }
  addQuantity(product: Product) {
    this.http.post(`${environment.apiUrl}/api/cart`, product).subscribe(data => {},
      error1 => {
        this.ngOnInit();
      });
  }
  dropProduct(product: Product){
    product.amount = product.amount - 1;
    console.log(product.amount)
    this.http.post(`${environment.apiUrl}/api/cart/negative`, product).subscribe(data => {},
      error => {
      this.ngOnInit();
      });
  }
  pay() {
    this.http.get(`${environment.apiUrl}/api/cart/pay`).subscribe(data => {},
      error => {
        console.log(error);
      });
  }

}
