import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  signUpLabel: string = 'Sign Up';
  logInLabel: string = 'Log In';
  isValid: boolean | null = null;

  userObj: any ={};

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService) { }

  loginForm = this.fb.group({
    username: [null, Validators.required],
    password: [null, Validators.required]
  })

  logIn(event: any){
    if (this.loginForm.invalid){
      return;
    }

    this.userService.login(this.loginForm.value)
      .subscribe(data => {
        if (data.success){
          let user = data.data;
          sessionStorage.setItem('userObj', JSON.stringify(user));
          sessionStorage.setItem('JWT', data.message);
          this.router.navigateByUrl('userFeed');
        } else {
          alert("Incorrect Username or Password");
        }
      },
      error => {})
  }

  @Output() toggle: EventEmitter<any> = new EventEmitter();
  toggleForm(data: string): void {
    this.toggle.emit(data);
  }

  ngOnInit(): void {
  }

}
