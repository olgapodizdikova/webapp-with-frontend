import {Component, NgModule, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {PasswordModule} from 'primeng/password';
import {UserService} from "../../service/user.service";
import {AuthService} from "../../core/auth.service";
import {TokenStorage} from "../../core/token.storage";

@NgModule({
  imports: [
    PasswordModule]
})

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})

export class UserLoginComponent implements OnInit {

  submitted: boolean = false;

  username: string;
  password: string;

  path: string;

  constructor(private userService: UserService,
              private router: Router,
              private authService: AuthService,
              private token: TokenStorage) {
  }

  ngOnInit() {
  }

  get errorLogin() {
    return JSON.stringify("Wrong Login or Password!!!");
  }

  login(): void {
    this.authService.login(this.username, this.password)
      .subscribe(
        data => {
          if (data != null) {
            this.token.saveToken(data.token);
            this.submitted = false;
            this.path = data.path;
            console.log(this.path);
            this.router.navigate([`${this.path}`]);
          }
          else {
            this.submitted = true;
          }
        },
        err => {
          this.submitted = true;
          console.log(err);
        });
  }

  /*toPath() {
    this.authService.getRoleUserByLogin(this.username)
      .subscribe(
        path => {
          this.path = path.toString();
          this.router.navigate([`${this.path}`]);
        },
        err => {
          this.submitted = true;
          console.log(err);
        });
  }*/

}
