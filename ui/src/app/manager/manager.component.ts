import { Component, OnInit } from '@angular/core';
import {User} from '@app/_models/user';
import {UserService} from '@app/_services/user.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';
import {AuthenticationService} from '@app/_services/authentication.service';
import {Role} from '@app/_models/Role';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  private users: User[];
  private role: string;
  private currentUser: User;

  constructor(private userService: UserService,
              private  http: HttpClient,
              private authenService: AuthenticationService) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data;
      console.log(data);
    });
    this.authenService.currentUser.subscribe(data => {
      for (const role of data.roles) {
        this.role = role.roleName;
      }
      console.log(this.role);
    });
  }

  remove(username: string) {
    if (confirm('Delete the account')) {
    this.userService.remove(username).subscribe(data => this.ngOnInit());
    }
  }
}
