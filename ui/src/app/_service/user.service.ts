import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { User } from '@app/_model/User'
import { environment } from "@environments/environment";
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl: string;



  constructor(private http: HttpClient) {
    this.usersUrl = environment.apiUrl + "/user/authenticate";
  }


  public findAll() {
    return this.http.post<User[]>(this.usersUrl, {
      "userName" : "cuong",
      "password" : "123"
    });
  }
}
