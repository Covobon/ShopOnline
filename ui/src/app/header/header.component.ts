import { Component, OnInit } from '@angular/core';
import {User} from "@app/_models/user";
import {Router} from "@angular/router";
import {AuthenticationService} from "@app/_services/authentication.service";

import { environment } from "@environments/environment";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  search: FormGroup;
  currentUser: User;
  logoUrl: string;
  loginUrl: string;
  
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.logoUrl = "http://www.matt-design.co.uk/img/year2/semester2/shop/logo.png";
    this.loginUrl= `${environment.url}/login`;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  ngOnInit() {
  }

  get f() {
    return this.search.controls;
  }

  onSubmit() {
//TODO

}
}
