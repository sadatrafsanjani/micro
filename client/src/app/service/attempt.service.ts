import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AttemptPayload} from "../dto/attempt-payload";

@Injectable({
  providedIn: 'root'
})
export class AttemptService {

  private url =  'http://localhost:8080/attempts';

  constructor(private http: HttpClient) {

  }

  postAttempt(payload: AttemptPayload){

    return this.http.post(this.url, payload, {observe: 'response'});
  }
}
