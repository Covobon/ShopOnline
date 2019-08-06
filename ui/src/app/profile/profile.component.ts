import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '@app/_services/authentication.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {regExpEscape} from '@ng-bootstrap/ng-bootstrap/util/util';
import {MustMatch} from '@app/_helpers/must-match.validator';
import {Router} from "@angular/router";
import {User} from "@app/_models/user";
import {UserService} from "@app/_services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  user: User;
  error: string;
  edit: boolean;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authen: AuthenticationService,
              private userService: UserService) {
    this.edit = true;
    this.user = new User;
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userName: [this.authen.currentUserValue.userName, Validators.required],
      fullName: [this.authen.currentUserValue.fullName, Validators.required],
      email: [this.authen.currentUserValue.email, [Validators.required, Validators.email]],
      address: [this.authen.currentUserValue.address],
      phoneNumber: [this.authen.currentUserValue.phoneNumber],
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    if (this.edit == false) {
      this.user.userName = this.f.userName.value;
      this.user.fullName = this.f.fullName.value;
      this.user.address = this.f.address.value;
      this.user.phoneNumber = this.f.phoneNumber.value;
      this.user.email = this.f.email.value;
      this.user.roles = this.authen.currentUserValue.roles;
      this.user.password = this.authen.currentUserValue.password;
      this.user.authdata = this.authen.currentUserValue.authdata;

      localStorage.setItem('currentUser', JSON.stringify(this.user));

      this.userService.profile(this.user).subscribe(data => console.log(data), error1 => console.log(error1));
    }
    console.log(this.user);
    this.edit = !this.edit;
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
