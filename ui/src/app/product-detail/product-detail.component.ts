import { Component, OnInit } from '@angular/core';
import {Product} from "@app/_models/product";
import {ProductService} from "@app/_services/product.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "@environments/environment";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  private product: Product;
  private productId: string;

  constructor(private productService: ProductService,
              private http: HttpClient,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.productId = this.route.snapshot.paramMap.get("productId");
    this.http.get<Product>(`${environment.apiUrl}/api/product?productId=${this.productId}`);
  }

}
