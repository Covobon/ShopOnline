import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '@app/_services/authentication.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {regExpEscape} from '@ng-bootstrap/ng-bootstrap/util/util';
import {MustMatch} from '@app/_helpers/must-match.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  error: string;
  registerForm: FormGroup;
  submitted = false;
  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
  ) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('^[a-z0-9_-]{3,15}$')]],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', Validators.required],
      password: ['', [Validators.required, Validators.pattern('^[a-z0-9_-]{3,15}$')]],
      confirmPassword: ['', Validators.required],
      address: ['', Validators.required]
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {

    this.submitted = true;

    if (this.registerForm.invalid) {
      return ;
    }

    this.loading = true;

    this.http.post<any>(`${environment.apiUrl}/api/user/register`, {
      "userName" : this.f.username.value,
      "email" : this.f.email.value,
      "password" : this.f.password.value,
      "address": this.f.address.value
    }).subscribe(
      data => {
        console.log(data);
      }, error => {
        this.error = error;
        this.loading = false;
      }
    );
  }

}
