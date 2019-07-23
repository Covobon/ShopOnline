import { Component, OnInit } from '@angular/core';
import { environment } from "@environments/environment";
import {InfoService} from "@app/_services/info.service";
import {AuthenticationService} from "@app/_services/authentication.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  urlLogo: string;
  categorys: string[];

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private info: InfoService,
    private authencitaion: AuthenticationService
  ) {
    if (this.authenticationService.currentUser){
      this.router.navigate(['/']);
      info.getCategory().subscribe(data => {
        this.categorys = data;
      });
    }
  }

  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    this.urlLogo = `${environment.backEndUrl}/files/logo.png`;
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      });
  }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data)
          this.router.navigate([this.returnUrl]);
        }
      )
  }

}
