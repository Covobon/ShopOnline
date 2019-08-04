import {Component, Injectable, Input, OnInit} from '@angular/core';
import {Product} from '@app/_models/product';
import {ProductService} from '@app/_services/product.service';
import {environment} from '@environments/environment';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  @Input() urlProduct: string;
  products: Product[];
  @Input() title: string;
  @Input() page: number;
  @Input() pageSize: number;
  @Input() showPagination: boolean;
  collectionSize: number;
  pager: {};

  constructor(private productServie: ProductService) { }

  ngOnInit() {
    this.productServie.find(`${this.urlProduct.substr(0, this.urlProduct.length - 1)}`)
      .subscribe(data => this.collectionSize = data.length);
    this.productServie.find(`${this.urlProduct}page=${this.page}&pageSize=${this.pageSize}`).subscribe(data => {
      this.products = data;
    });
    /*this.page = 1;
    this.pageSize = 10;
    this.productServie.find(`${environment.apiUrl}/api/product`).subscribe(data => {
      this.collectionSize = data.length;
    });

    this.productServie.find(`${environment.apiUrl}/api/product?page=${this.page}&$pageSize={this.pageSize}`).subscribe(data => {
      this.products = data;
    });*/
  }
  loadPage(page: number) {
    this.page = Math.ceil(page);
    this.productServie.find(`${this.urlProduct}page=${this.page}&pageSize=${this.pageSize}`).subscribe(data => {
      this.products = data;
    });
  }
  checkNext() {
    if (this.page < Math.ceil(this.collectionSize / this.pageSize)) {
      return true;
    }
    return false;
  }
}
