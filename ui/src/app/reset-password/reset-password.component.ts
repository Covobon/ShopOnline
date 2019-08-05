import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MustMatch} from "@app/_helpers/must-match.validator";
import {AuthenticationService} from "@app/_services/authentication.service";

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

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService) {
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

    if (this.resetPass.invalid){
      return;
    }
    console.log("commited");
    this.authenticationService.resetPassword(this.authen, this.expired, this.f.password.value).subscribe(
      data => {console.log(data)}
    );
  }
}
