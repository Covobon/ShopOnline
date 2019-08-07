import { Component, OnInit } from '@angular/core';
import {UserService} from "@app/_services/user.service";
import {Orders} from "@app/_models/orders";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  private orderses: Orders[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.history().subscribe(data => {
      this.orderses = data;
      console.log(data);
    })
  }

}
