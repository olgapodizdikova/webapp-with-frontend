import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Role} from "../domain/role";

@Injectable()
export class RoleService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Role[]>{
    return this.http.get<Role[]>('/api/role');
  }

}
