import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '@app/_services/authentication.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  forgotPassword: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.forgotPassword = false;
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    this.loading = true;
    if (this.forgotPassword === false) {
      if (this.loginForm.invalid) {
        return;
      }

      this.authenticationService.login(this.f.username.value, this.f.password.value)
        .pipe(first())
        .subscribe(
          data => {
            if (data == null){
            } else {
              this.router.navigate([this.returnUrl]);
            }
          },
          error => {
            console.log(error);
            this.error = 'Invalid username or password';
            this.loading = false;
          }
        );
    } else {
      this.authenticationService.forgot(this.f.username.value).subscribe(
        data => {
          if (data == null) {
            this.error = 'Username not exists!';
            this.loading = false;
            return;
          }

        },
        error1 => {
          alert("Send to email");
          this.router.navigate(['/']);
        }
      );

    }
  }

  checkForgot() {
    this.forgotPassword = !this.forgotPassword;
  }
}
