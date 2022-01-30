import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "../dto/user-response";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url =  environment.SERVER_URL + '/users';

  constructor(private http: HttpClient) {

  }

  getUsers(idList: number[]): Observable<UserResponse[]> {

    return this.http.get<UserResponse[]>(this.url + '/' + idList);
  }
}
