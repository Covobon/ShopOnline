import { Component, OnInit } from '@angular/core';
import {User} from '@app/_models/user';
import {UserService} from '@app/_services/user.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  private users: User[];

  constructor(private userService: UserService,
              private  http: HttpClient) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data;
      console.log(data);
    });
  }

  remove(username: string) {
    if (confirm('Some thing')) {
    this.userService.remove(username);
    for (let i = 1; i < this.users.length; i++) {
      if (this.users[i].userName === username) {
        this.users[i] = null;
      }
    }
    }
  }
}
