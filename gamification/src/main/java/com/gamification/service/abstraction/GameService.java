package com.gamification.service.abstraction;

import com.gamification.domain.GameResult;
import com.challenge.ChallengeSolvedEvent;

public interface GameService {

    GameResult newAttemptForUser(ChallengeSolvedEvent challenge);
}