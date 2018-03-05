import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorage} from "../../core/token.storage";

@Component({
  selector: 'app-user-welcome',
  templateUrl: './user-welcome.component.html',
  styleUrls: ['./user-welcome.component.css']
})
export class UserWelcomeComponent implements OnInit {

  constructor(private router: Router,
              private token: TokenStorage) { }

  ngOnInit() {
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['login']);
  }

}
