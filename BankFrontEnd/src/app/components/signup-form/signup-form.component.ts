import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {

  userId!: number;
  _isInNav : boolean = true;
  logOutLabel: string = 'Logout';
  profilePosition: string = "-64rem";
  observer: Subscription = new Subscription();
  userList: Array<any> = [];
  listTemp: Array<User> = [];

  constructor() { }

  ngOnInit(): void {
    this.userId = JSON.parse(sessionStorage.getItem('userObj')!).userId;

  }

}
