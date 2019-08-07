import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '@app/_services/authentication.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {regExpEscape} from '@ng-bootstrap/ng-bootstrap/util/util';
import {MustMatch} from '@app/_helpers/must-match.validator';
import {Router} from "@angular/router";
import {User} from "@app/_models/user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  user: User;
  error: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authen: AuthenticationService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userName: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      acceptTerms: [false, Validators.requiredTrue]
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.user = new User();

    this.user.userName = this.f.userName.value;
    this.user.fullName = this.f.fullName.value;
    this.user.email = this.f.email.value;
    this.user.password = this.f.password.value;

    // display form values on success
    this.authen.register(this.user).subscribe(data => {
      this.authen.login(this.user.userName, this.user.password).subscribe(data => {
        alert("Send email verify");
        this.router.navigate(['/'])
      });
    }, error => {
      this.error = `Username or email exists!`;
    });
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
