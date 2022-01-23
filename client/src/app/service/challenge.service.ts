import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ChallengeResponse} from "../dto/challenge-response";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  private url =  'http://localhost:8080/challenges';

  constructor(private http: HttpClient) {

  }

  getRandomChallenge(): Observable<ChallengeResponse> {

    return this.http.get<ChallengeResponse>(this.url + '/random');
  }
}
