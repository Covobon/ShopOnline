import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '@app/_models/user';
import { UserService } from '@app/_services/user.service';
import {ProductService} from "@app/_services/product.service";
import {Product} from "@app/_models/product";
import {environment} from "@environments/environment";

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
  loading = false;
  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.loading = true;
    this.productService.find(`${environment.apiUrl}/api/product`)
      .subscribe(data => this.products = data);
  }
}
