import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
/*
import { AuthGuard } from './_helpers/auth.guard';
*/
import {RegisterComponent} from "@app/register/register.component";
import {WhitePageComponent} from "@app/white-page/white-page.component";
import {AuthGuard} from "@app/_helpers/auth.guard";

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},

  // otherwise redirect to home
  { path: '**', component: WhitePageComponent }
];

export const appRoutingModule = RouterModule.forRoot(routes);
