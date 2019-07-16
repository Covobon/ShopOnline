import { Component, OnInit } from '@angular/core';
import { User } from '@app/_model/User';
import { UserService } from '@app/_service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
      console.log(data);
    });
  }
}
