import { Component, OnInit } from '@angular/core';
import {User} from '@app/_models/user';
import {Router} from '@angular/router';
import {AuthenticationService} from '@app/_services/authentication.service';

import { environment } from '@environments/environment';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {SearchComponent} from '@app/search/search.component';

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
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.logoUrl = 'http://www.matt-design.co.uk/img/year2/semester2/shop/logo.png';
    this.loginUrl = `${environment.url}/login`;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  ngOnInit() {
    this.search = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  get f() {
    return this.search.controls;
  }

  onSubmit() {
    if (this.search.invalid) {
      return;
    }
    this.router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(() =>
      this.router.navigate(['/search'], { queryParams: { value : this.f.name.value } }));
  }
}
