import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MustMatch} from "@app/_helpers/must-match.validator";
import {AuthenticationService} from "@app/_services/authentication.service";
import {UserService} from "@app/_services/user.service";
import {User} from "@app/_models/user";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  resetPass: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  authen: string;
  expired: string;
  theUser: User;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService) {
    this.route.queryParams.subscribe(params => {
      this.authen = params.x;
      this.expired = params.y
    });
  }

  ngOnInit() {
    this.resetPass = this.formBuilder.group({
/*
      password: ['', [Validators.required, Validators.pattern('^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$')]],
*/
      currentPassword: [''],
      password: ['', Validators.required],
      repassword: ['', Validators.required]
    }, {
      validators: MustMatch('password', 'repassword')
    });
  }

  get f() {
    return this.resetPass.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.resetPass.invalid){
      return;
    }
    if (this.authen) {
      this.authenticationService.resetPassword(this.authen, this.expired, this.f.password.value).subscribe();
      alert("Change password success, to home page");
      this.router.navigate(['/']);
      console.log(this.authen);
    } else {
      this.theUser = this.authenticationService.currentUserValue;
      this.theUser.password = this.f.password.value;
      const currentAddress =  this.theUser.address;
      this.theUser.address = this.f.currentPassword.value; // save current password to address to send to server
      this.userService.setPassword(this.theUser).subscribe(data => {
        console.log('data' + data);
        this.theUser.authdata = window.btoa(this.theUser.userName + ":" + this.theUser.password);
        this.theUser.address = currentAddress;
        localStorage.setItem('currentUser', JSON.stringify(this.theUser));
        this.router.navigate(['/'])
        alert("Change password success");
      }, error => {
        console.log('error' + error);
        this.error = 'Current password invalid!';
        return;
      });
    }
  }
}
