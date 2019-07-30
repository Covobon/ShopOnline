import { Component, OnInit } from '@angular/core';
import {Product} from '@app/_models/product';
import {CartProduct} from '@app/_models/cart-product';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {ProductService} from '@app/_services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private products: Product[];
  private cartProducts: CartProduct[];

  constructor(private http: HttpClient,
              private productService: ProductService) { }

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
      if (product.productId === value.productId){
        return;
      }
    });
    product.amount = 1;
    this.products[this.products.length] = product;
  }
}
