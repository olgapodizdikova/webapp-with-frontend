import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

import {User} from "../../domain/user";
import {UserService} from "../../service/user.service";
import {LazyLoadEvent} from "primeng/api";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  user: User = new User();

  selectedUser: User;

  users: User[];

  tables: any[];

  loading: boolean;

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.userService.findAll()
      .subscribe(data => {
          this.users = data
        },
        err => {
          console.log(err);
        });
    this.tables = [
      {field: 'login', header: 'Логин'},
      {field: 'email', header: 'E-mail'},
      {field: 'age', header: 'Возраст'},
      {field: 'salary', header: 'З/П'}
    ];
  }

  delete(): void {
    this.userService.delete(this.selectedUser.id)
      .subscribe(data => {
        this.users = this.users.filter(u => u !== this.selectedUser);
      });
    this.user = null;
  }

  edit() {
    this.router.navigate(['userAdd/' + this.selectedUser.id]);
  }


}
