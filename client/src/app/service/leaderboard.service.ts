import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LeaderboardResponse} from "../dto/leaderboard-response";

@Injectable({
  providedIn: 'root'
})
export class LeaderboardService {

  private url =  'http://localhost:8080/leaderboards';

  constructor(private http: HttpClient) {

  }

  getLeaderboards(): Observable<LeaderboardResponse[]> {

    return this.http.get<LeaderboardResponse[]>(this.url);
  }
}
