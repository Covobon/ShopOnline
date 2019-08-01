import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

import {RegisterComponent} from '@app/register/register.component';
import {WhitePageComponent} from '@app/white-page/white-page.component';
import {AuthGuard} from '@app/_helpers/auth.guard';
import {ManagerGuard} from '@app/_helpers/manager.guard';
import {ProductDetailComponent} from '@app/product-detail/product-detail.component';
import {ManagerComponent} from '@app/manager/manager.component';
import {CartComponent} from '@app/cart/cart.component';
import {SearchComponent} from '@app/search/search.component';
import {ForbeddenComponent} from '@app/forbedden/forbedden.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'cart', component: CartComponent , canActivate: [AuthGuard] },
  { path: 'products/:productId', component: ProductDetailComponent},
  { path: 'manager', component : ManagerComponent , canActivate: [ManagerGuard]},
  { path: 'search', component: SearchComponent},
  { path: 'forbedden', component: ForbeddenComponent },
  { path: '**', component: WhitePageComponent }
];

export const appRoutingModule = RouterModule.forRoot(routes);
