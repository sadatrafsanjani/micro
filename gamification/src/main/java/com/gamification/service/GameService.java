package com.gamification.service;

import com.gamification.domain.GameResult;
import com.gamification.dto.ChallengeSolvedDTO;

public interface GameService {

    GameResult newAttemptForUser(ChallengeSolvedDTO challenge);
}