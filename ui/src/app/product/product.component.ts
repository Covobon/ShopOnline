import {Component, Input, OnInit} from '@angular/core';
import {Product} from "@app/_models/product";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() product: Product;
  private src: string;

  constructor() {
  }

  ngOnInit() {
    this.src = `http://localhost:8081/api/img/${this.product.category.toLowerCase()}/${this.product.images[0].imageName}`;
  }

}
