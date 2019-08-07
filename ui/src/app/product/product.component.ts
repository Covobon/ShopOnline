import {Component, Input, OnInit} from '@angular/core';
import {Product} from '@app/_models/product';
import {HttpClient} from '@angular/common/http';
import {ProductService} from '@app/_services/product.service';
import {environment} from '@environments/environment';
import {AuthenticationService} from "@app/_services/authentication.service";


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() product: Product;
  private src: string;

  constructor(private productService: ProductService,
              private authen: AuthenticationService,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.src = `http://localhost:8081/api/img/${this.product.category.toLowerCase()}/${this.product.images[0].imageName}`;
  }
  addToCart(product: Product) {

    if (this.authen.currentUserValue) {
      alert("Add to cart");
      this.productService.post(`${environment.apiUrl}/api/cart`, product).subscribe((reponse: any) => {
        console.log(reponse)
      });
    } else {
      alert("You must login");
    }
/*
    this.http.post<String>(`${environment.apiUrl}/api/cart`, {"productId" : product.productId}).subscribe(reponse => console.log(reponse));
*/
  }

}
