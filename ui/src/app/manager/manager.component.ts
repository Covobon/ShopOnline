import { Component, OnInit } from '@angular/core';
import {User} from '@app/_models/user';
import {UserService} from '@app/_services/user.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {AuthenticationService} from '@app/_services/authentication.service';
import {Role} from '@app/_models/Role';
import {Product} from "@app/_models/product";
import {ProductService} from "@app/_services/product.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Orders} from "@app/_models/orders";

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  private roles: Role[] = [{
    roleId: 1,
    roleName: "USER",
  },{
    roleId: 2,
    roleName: "SELLER",
  },{
    roleId: 3,
    roleName: "MARKETING",
  },{
    roleId: 4,
    roleName: "MANAGER",
  },
  ];

  private users: User[];
  private role: string;
  private currentUser: User;
  private products: Product[];
  addProduct: FormGroup;
  submitted: boolean;
  error = '';
  private checkOrders = true;
  private checkProducts = false;
  private checkUser = false;
  createUser: FormGroup;
  checkCreate = false;
  private user: User;
  private orderses: Orders[];

  constructor(private userService: UserService,
              private  http: HttpClient,
              private authenService: AuthenticationService,
              private productService: ProductService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.http.get<Orders[]>(`${environment.apiUrl}/api/orders`).subscribe(
      data => {this.orderses = data;}
    )
    this.createUser = this.formBuilder.group({
      userName: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ''
    });
    this.userService.getAll().subscribe(data => {
      this.users = data;
      console.log(data);
    });
    this.authenService.currentUser.subscribe(data => {
      for (const role of data.roles) {
        this.role = role.roleName;
      }
      console.log(this.role);
    });
    this.productService.find(`${environment.apiUrl}/api/product`).subscribe(data => {
      this.products = data;
    });
  }

  get f() {
    return this.createUser.controls;
  }

  remove(username: string) {
    if (confirm('Delete the account')) {
    this.userService.remove(username).subscribe(data => this.ngOnInit());
    }
  }

  src(product: Product): string {
    return `http://localhost:8081/api/img/${product.category.toLowerCase()}/${product.images[0].imageName}`;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.createUser.invalid) {
      return;
    }

    this.user = new User();

    this.user.userName = this.f.userName.value;
    this.user.fullName = this.f.fullName.value;
    this.user.email = this.f.email.value;
    this.user.password = this.f.userName.value;
    console.log(this.f.role.value);
    switch (this.f.role.value) {
      case "2": {
        this.user.roles = this.roles.slice(0, 2);
        break;
      }
      case "3": {
        this.user.roles = this.roles.slice(0, 3);
        break;
      }
      case "4": {
        this.user.roles = this.roles;
        break;
      }
      default: {
        this.user.roles = this.roles.slice(0, 1);
      }
    }
    // display form values on success
    this.userService.post(this.user).subscribe(
      data => {
        this.submitted = false;
        this.checkCreate = false;
        this.ngOnInit();
      }
    );

  }

  showOrders() {
    this.checkOrders = true;
    this.checkProducts = false;
    this.checkUser = false;
  }

  showProducts() {
    this.checkProducts = true;
    this.checkOrders = false;
    this.checkUser = false;
  }

  showUsers() {
    this.checkUser = true;
    this.checkOrders = false;
    this.checkProducts = false;
  }

  edit(user: User) {

  }

  create() {
    this.checkCreate = !this.checkCreate;
  }
}
