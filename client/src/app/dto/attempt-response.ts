import {UserResponse} from "./user-response";

export class AttemptResponse {

  id!: number;
  user!: UserResponse;
  factorA!: number;
  factorB!: number;
  resultAttempt!: number;
  correct!: boolean;
}
