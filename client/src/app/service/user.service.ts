import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "../dto/user-response";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url =  'http://localhost:8080/users';

  constructor(private http: HttpClient) {

  }

  getUsers(idList: number[]): Observable<UserResponse[]> {

    return this.http.get<UserResponse[]>(this.url + '/' + idList);
  }
}
