import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {environment} from "@environments/environment";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  urlProduct: string;
  param: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.param = this.route.snapshot.paramMap.get('param');
    this.urlProduct = `${environment.apiUrl}/api/product?category=${this.param}&`;
  }

}
