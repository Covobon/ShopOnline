import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';


import { appRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {BasicAuthInterceptor} from '@app/_helpers/basic-auth.interceptor';
import {ErrorInterceptor} from '@app/_helpers/error.interceptor';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { ListProductComponent } from './list-product/list-product.component';
import { RegisterComponent } from './register/register.component';
import { WhitePageComponent } from './white-page/white-page.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ProductComponent } from './product/product.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ManagerComponent } from './manager/manager.component';
import { CartComponent } from './cart/cart.component';
import { SearchComponent } from './search/search.component';
import { ForbeddenComponent } from './forbedden/forbedden.component';
import { VerifyComponent } from './verify/verify.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ProfileComponent } from './profile/profile.component';
import {ToastrModule} from 'ngx-toastr';
import { FileSelectDirective } from 'ng2-file-upload';

import { CategoryComponent } from './category/category.component';
import { UploadComponent } from './upload/upload.component';
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { HistoryComponent } from './history/history.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    ListProductComponent,
    RegisterComponent,
    WhitePageComponent,
    ProductComponent,
    ProductDetailComponent,
    ManagerComponent,
    CartComponent,
    SearchComponent,
    ForbeddenComponent,
    VerifyComponent,
    ResetPasswordComponent,
    ProfileComponent,
    CategoryComponent,
    UploadComponent,
    FileSelectDirective,
    HistoryComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    appRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
