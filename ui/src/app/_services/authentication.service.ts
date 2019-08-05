import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "@app/_models/user";
import {HttpClient} from "@angular/common/http";
import {environment} from "@environments/environment";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.apiUrl}/api/user/login`, {"userName":username, "password": password})
      .pipe(map(user => {
        user.authdata = window.btoa(username + ":" + password);
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return user;
      }))
  }

  register(user: User){
    return this.http.post<string>(`${environment.apiUrl}/api/user/register`, user);
  }

  verify(auth: string, expired: string){
    return this.http.get<any>(`${environment.apiUrl}/api/user/verify?x=${auth}&y=${expired}`).pipe(map(user => {
      user.authdata = auth;
      localStorage.setItem('currentUser', JSON.stringify(user));
      this.currentUserSubject.next(user);
      return user;
    }))
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  forgot(username: string) {
    return this.http.get(`${environment.apiUrl}/api/user/password?username=${username}`);
  }

  resetPassword(auth: string, expired: string, password: string) {
    return this.http.get<any>(`${environment.apiUrl}/api/user/verify?x=${auth}&y=${expired}&p=${password}`);

    }
}
