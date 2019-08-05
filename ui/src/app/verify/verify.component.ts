import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "@app/_services/authentication.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent implements OnInit {

  authen: string;
  expired: string;

  constructor(private activatedRoute: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private router: Router) {
    this.activatedRoute.queryParams.subscribe(params => {
      this.authen = params.x;
      this.expired = params.y;
    });
    if (authenticationService.currentUserValue){
      this.router.navigate(['/']);
    }
    this.authenticationService.verify(this.authen, this.expired)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['/']);
        },
        error => {
          this.router.navigate(['/forbedden']);
        }
      );

  }

  ngOnInit() {


  }

}
