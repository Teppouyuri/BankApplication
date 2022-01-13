import { UserService } from './../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  current: string = 'login';

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('userObj') != null){
      this.router.navigateByUrl('');
    }
  }

  forgotForm = this.fb.group({
    username: [null, Validators.required]
  })

  toggle(data: string){
    this.current = data;
  }

  backToLogin(){
    this.current = 'login';
  }


}
