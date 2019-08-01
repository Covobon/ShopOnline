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
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      fullname: ['', Validators.required],
      address: ['', Validators.required]
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
      userName : this.f.username.value,
      email : this.f.email.value,
      password : this.f.password.value,
      address : this.f.address.value,
      fullName : this.f.fullname.value
    }).subscribe(
      data => {
        console.log(data);
      }, error => {
        this.error = 'Account or email exists!';
        this.loading = false;
      }
    );
  }

}
