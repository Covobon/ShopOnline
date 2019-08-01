import {Component, OnInit} from '@angular/core';
import {first} from 'rxjs/operators';

import {User} from '@app/_models/user';
import {UserService} from '@app/_services/user.service';
import {ProductService} from '@app/_services/product.service';
import {Product} from '@app/_models/product';
import {environment} from '@environments/environment';

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
  loading = false;
  products: Product[];
  page: number;

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.loading = true;
  }

  loadPage(page: number) {
    this.page = Math.ceil(page);
    this.productService.find(`${environment.apiUrl}/api/product?page=${this.page}&pageSize=10`).subscribe(data => {
      this.products = data;
    });
  }
}
