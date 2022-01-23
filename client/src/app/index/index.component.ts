import { Component, OnInit } from '@angular/core';
import {NgxSpinnerService} from "ngx-spinner";
import {ToastrService} from "ngx-toastr";
import {ChallengeService} from "../service/challenge.service";
import {ChallengeResponse} from "../dto/challenge-response";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AttemptPayload} from "../dto/attempt-payload";
import {AttemptService} from "../service/attempt.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  challengeResponse!: ChallengeResponse;
  attemptForm: FormGroup;
  private attemptPayload!: AttemptPayload;

  constructor(private challengeService: ChallengeService,
              private attemptService: AttemptService,
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
      this.toastr.success(JSON.stringify(response));
    }, error => {
      this.toastr.error('Error!');
    });

    this.attemptForm.patchValue({'alias': '', 'guess': ''});
  }
}
