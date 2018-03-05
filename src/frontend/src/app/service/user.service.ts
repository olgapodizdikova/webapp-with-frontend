import {Injectable} from '@angular/core';
import {User} from "../domain/user";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>('/api/person');
  }

  findById(id: number): Observable<User> {
    return this.http.get<User>(`/api/person/${id}`);
  }

  save(user: User): Observable<{}> {
    return this.http.post<User>(`/api/person/`, user);
  }

  update(user: User, id: number): Observable<{}> {
    return this.http.put<User>(`/api/person/${id}`, user);
  }

  delete(id: number): Observable<{}> {
    return this.http.delete(`/api/person/${id}`);
  }
}
