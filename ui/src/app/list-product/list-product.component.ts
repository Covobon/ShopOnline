import {Component, Injectable, Input, OnInit} from '@angular/core';
import {Product} from "@app/_models/product";
import {ProductService} from "@app/_services/product.service";
import {environment} from "@environments/environment";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  @Input() products: Product[];
  @Input() title: string;
  page: number;
  pageSize: number;

  constructor(private productServie: ProductService) { }

  ngOnInit() {
    this.page = 1;
    this.pageSize = 10;
    this.productServie.find(`${environment.apiUrl}/api/product?productId=Laptop101`).subscribe(data => {
      this.products = data;
    });
  }

}
