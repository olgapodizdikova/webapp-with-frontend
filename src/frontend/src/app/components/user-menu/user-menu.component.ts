import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {MenuItem} from "primeng/api";
import {UserService} from "../../service/user.service";
import {TokenStorage} from "../../core/token.storage";

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  items: MenuItem[];

  constructor(private userService: UserService,
              private router: Router,
              private token: TokenStorage) {
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Список пользователей',
        icon: 'fa-book',
        command: (event: any) => {
          this.router.navigate(['userList'])
        },
        items: [
          {
            label: 'Добавить пользователя',
            icon: 'fa-plus',
            command: (event: any) => {
              this.router.navigate(['userAdd'])
            }
          }]
      }
    ];
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['login']);
  }

}
