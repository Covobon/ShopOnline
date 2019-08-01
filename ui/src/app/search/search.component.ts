import { Component, OnInit } from '@angular/core';
import {environment} from '@environments/environment';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  params: string;
  urlProduct: string;
  page: number;
  pageSize: number;
  value: string;

  constructor(private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.value = params.value;
    });
  }

  ngOnInit() {
    this.page = 1;
    this.pageSize = 10;
    this.urlProduct = `${environment.apiUrl}/api/product?name=${this.value}&`;
  }
  search(params: string) {
    this.params = params;
    this.ngOnInit();
  }


}
