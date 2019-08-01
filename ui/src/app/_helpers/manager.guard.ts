import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthenticationService } from '@app/_services/authentication.service';

@Injectable({providedIn: 'root' })
export class ManagerGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser) {
      if (currentUser.roles.length > 1) {
        return true;
      }
      this.router.navigate(['/forbedden']);
      return false;
    }

    this.router.navigate(['/login'], {queryParams: {message: 'You must login to access' }});
    return false;
  }
}
