import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import "rxjs/add/operator/shareReplay";
import {Observable} from "rxjs/Observable";
import {User} from "../domain/user";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    const credentials = {username: username, password: password};
    console.log('attempAuth ::');
    return this.http.post<any>('/api/login', credentials);
  }

}
