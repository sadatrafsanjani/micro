import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LeaderboardResponse} from "../dto/leaderboard-response";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LeaderboardService {

  private url =  environment.SERVER_URL + '/leaderboards';

  constructor(private http: HttpClient) {

  }

  getLeaderboards(): Observable<LeaderboardResponse[]> {

    return this.http.get<LeaderboardResponse[]>(this.url);
  }
}
