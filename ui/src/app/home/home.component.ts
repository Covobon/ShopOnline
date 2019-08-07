import {Component, OnInit} from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '@app/_models/user';
import { UserService } from '@app/_services/user.service';
import {ProductService} from '@app/_services/product.service';
import {Product} from '@app/_models/product';
import {environment} from '@environments/environment';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit {
  loading = false;
  products: Product[];
  urlProduct: string;

  collectionSize: number;
  urlPhone: string;
  urlLaptop: string;
  urlTablet: string;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.loading = true;
    this.urlProduct = `${environment.apiUrl}/api/product?`;
    this.urlPhone = `${environment.apiUrl}/api/product?category=Phone&`;
    this.urlLaptop = `${environment.apiUrl}/api/product?category=Laptop&`;
    this.urlTablet = `${environment.apiUrl}/api/product?category=Tablet&`;
  }
}
