import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '@app/_models/user';
import { UserService } from '@app/_services/user.service';
import {ProductService} from "@app/_services/product.service";
import {Product} from "@app/_models/product";

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
  loading = false;
  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.loading = true;
    this.productService.find()
      .subscribe(data => this.products = data);
  }
}
