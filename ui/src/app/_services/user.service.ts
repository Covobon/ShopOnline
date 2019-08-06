import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { environment } from '@environments/environment';
import {User} from '@app/_models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
  ) {}
  getAll() {
    return this.http.get<User[]>(`${environment.apiUrl}/api/user`);
  }

  remove(username: string) {
    return this.http.delete(`${environment.apiUrl}/api/user?userName=${username}`);
  }

  profile(user: User) {
    return this.http.put<User>(`${environment.apiUrl}/api/user/profile`, user);
  }

  setPassword(user: User) {
    return this.http.post<User>(`${environment.apiUrl}/api/user/setpassword`, user);
  }
}
