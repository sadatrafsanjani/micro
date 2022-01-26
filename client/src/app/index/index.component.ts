import { Component, OnInit } from '@angular/core';
import {NgxSpinnerService} from "ngx-spinner";
import {ToastrService} from "ngx-toastr";
import {ChallengeService} from "../service/challenge.service";
import {ChallengeResponse} from "../dto/challenge-response";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AttemptPayload} from "../dto/attempt-payload";
import {AttemptService} from "../service/attempt.service";
import {AttemptResponse} from "../dto/attempt-response";
import {LeaderboardService} from "../service/leaderboard.service";
import {UserService} from "../service/user.service";
import {LeaderboardResponse} from "../dto/leaderboard-response";
import { faMedal } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  faMedal = faMedal;
  challengeResponse!: ChallengeResponse;
  attempts: AttemptResponse[] = [];
  leaderboards: LeaderboardResponse[] = [];
  attemptForm: FormGroup;
  private attemptPayload!: AttemptPayload;

  constructor(private challengeService: ChallengeService,
              private attemptService: AttemptService,
              private leaderboardService: LeaderboardService,
              private userService: UserService,
              private toastr: ToastrService,
              private spinner: NgxSpinnerService) {
    this.attemptForm = new FormGroup({
      guess: new FormControl('', [Validators.required, Validators.minLength(2)]),
      alias: new FormControl('', [Validators.required, Validators.minLength(2)])
    });
  }

  ngOnInit(): void {

    this.attemptPayload = {
      factorA: 0,
      factorB: 0,
      userAlias: '',
      guess: 0
    };

    this.getRandomChallenge();
    this.refreshLeaderBoard();
    setInterval(this.refreshLeaderBoard.bind(this), 5000);
  }

  private refreshLeaderBoard(){

    this.leaderboardService.getLeaderboards().subscribe(response => {

      let userIds = response.map(row => row.userId);

      this.userService.getUsers(userIds).subscribe(users => {

        let userMap = new Map();
        users.forEach(user => {
          userMap.set(user.id, user.userAlias);
        });

        response.forEach(row => {
            row['alias'] = userMap.get(row.userId);
        });

        this.updateLeaderBoard(response);
      });
    });
  }

  private getRandomChallenge(){

    this.challengeService.getRandomChallenge().subscribe(response => {
      this.challengeResponse = response;
    },
      error => {
      this.toastr.error('Error!');
      });
  }

  get alias() {
    return this.attemptForm.get('alias');
  }

  get guess() {
    return this.attemptForm.get('guess');
  }

  onSubmit(){

    this.attemptPayload.factorA = this.challengeResponse.factorA;
    this.attemptPayload.factorB = this.challengeResponse.factorB;
    this.attemptPayload.userAlias = this.alias?.value;
    this.attemptPayload.guess = this.guess?.value;

    this.attemptService.postAttempt(this.attemptPayload).subscribe(response => {
      this.getLast10AttemptsByAlias(this.attemptPayload.userAlias);
      this.getRandomChallenge();
    }, error => {
      this.toastr.error('Error!');
    });

    this.attemptForm.patchValue({'alias': '', 'guess': ''});
  }

  private getLast10AttemptsByAlias(alias: string){

    this.attemptService.getLast10AttemptsByAlias(alias).subscribe(response => {
      this.attempts = response;
    },error => {
      this.toastr.error('Error!');
    });
  }

  isCorrect(correct: boolean){

    return correct ? "text-success" : "text-danger";
  }

  getBadgeColor(badge: string){

    const colorMap = new Map();

    colorMap.set('Bronze', 'badge-bronze');
    colorMap.set('Silver', 'badge-silver');
    colorMap.set('Gold', 'badge-gold');
    colorMap.set('First time', 'badge-first-time');
    colorMap.set('Lucky Number', 'badge-lucky-number');

    return colorMap.get(badge);
  }

  private updateLeaderBoard(leaderboardResponse: LeaderboardResponse[]){

    this.leaderboards = leaderboardResponse;
  }
}
