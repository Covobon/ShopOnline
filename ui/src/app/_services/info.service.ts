import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class InfoService {

  constructor(
    private http: HttpClient,
  ) {}

  getCategory() {
    return this.http.get<string[]>(`${environment.backEndUrl}/public/category`);
  }
}
