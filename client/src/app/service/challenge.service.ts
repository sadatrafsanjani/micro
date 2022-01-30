import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ChallengeResponse} from "../dto/challenge-response";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {

  private url =  environment.SERVER_URL + '/challenges';

  constructor(private http: HttpClient) {

  }

  getRandomChallenge(): Observable<ChallengeResponse> {

    return this.http.get<ChallengeResponse>(this.url + '/random');
  }
}
