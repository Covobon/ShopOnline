import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "@app/_services/authentication.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "@environments/environment";

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
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      repassword: ['', Validators.required],
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {

    this.submitted = true;

    /*if (this.registerForm.invalid) {
      return ;
    }*/

    this.loading = true;

    this.http.post<any>(`${environment.apiUrl}/api/user/register`, {
      "userName" : this.f.username.value,
      "email" : this.f.email.value,
      "password" : this.f.password.value
    }).subscribe(
      data => {
        console.log(data);
      },error => {
        this.error = error;
        this.loading = false;
      }
    )


  }

}
