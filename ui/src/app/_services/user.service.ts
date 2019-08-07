import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import { environment } from '@environments/environment';
import {User} from '@app/_models/user';
import {Orders} from "@app/_models/orders";

/*
interface SearchResult {
  users: User[];
  total: number;
}

interface State {
  page: number;
  pageSize: number;
  searchTerm: string;
  sortColumn: string;
  sortDirection: SortDirection;
}

function compare(v1, v2) {
  return v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
}

function sort(users: User[], column: string, direction: string): User[] {
  if (direction === '') {
    return users;
  } else {
    return [...users].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
}

function  matches(user: User, term: string, pipe: PipeTransform) {
  return user.userName.toLowerCase().includes(term.toLowerCase())
    || user.fullName.toLowerCase().includes(term.toLowerCase())
    || user.address.toLowerCase().includes(term.toLowerCase())
    || user.phoneNumber.toLowerCase().includes(term.toLowerCase())
    || pipe.transform(user.lastAccess).includes(term)
    || pipe.transform(user.createTime).includes(term);
}
*/

@Injectable({
  providedIn: 'root'
})
export class UserService {

 /* private _loading$ = new BehaviorSubject<boolean>(true);
  private _search$ = new Subject<void>();
  private _users$ = new BehaviorSubject<User[]>([]);
  private _total$ = new BehaviorSubject<number>(0);

  private _state: State = {
    page: 1,
    pageSize: 4,
    searchTerm: '',
    sortColumn: '',
    sortDirection: ''
  };*/

  constructor(private http: HttpClient){
    /*this._search$.pipe(
      tap(() => this._loading$.next(true)),
      debounceTime(200),
      switchMap(() => this._search()),
      delay(200),
      tap(() => this._loading$.next(false))
    ).subscribe(result => {
      this._users$.next(result.users);
      this._total$.next(result.total);
    });

    this._search$.next();*/
  }

  /*get users$() { return this._users$.asObservable(); }
  get total$() { return this._total$.asObservable(); }
  get loading$() { return this._loading$.asObservable(); }
  get page() { return this._state.page; }
  get pageSize() { return this._state.pageSize; }
  get searchTerm() { return this._state.searchTerm; }

  set page(page: number) { this._set({page}); }
  set pageSize(pageSize: number) { this._set({pageSize}); }
  set searchTerm(searchTerm: string) { this._set({searchTerm}); }
  set sortColumn(sortColumn: string) { this._set({sortColumn}); }
  set sortDirection(sortDirection: SortDirection) { this._set({sortDirection}); }

  private _set(patch: Partial<State>) {
    Object.assign(this._state, patch);
    this._search$.next();
  }

  private _search(): Observable<SearchResult> {
    const {sortColumn, sortDirection, pageSize, page, searchTerm} = this._state;

    // 1. sort
    let users = sort(USERS, sortColumn, sortDirection);

    // 2. filter
    users = users.filter(user => matches(user, searchTerm, this.pipe));
    const total = users.length;

    // 3. paginate
    users = users.slice((page - 1) * pageSize, (page - 1) * pageSize + pageSize);
    return of({users, total});
  }*/



  getAll() {
    return this.http.get<User[]>(`${environment.apiUrl}/api/user`);
  }

  post(user: User) {
    return this.http.post(`${environment.apiUrl}/api/user`, user);
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

  history() {
    return this.http.get<Orders[]>(`${environment.apiUrl}/api/orders/history`);
  }

}
