package com.multiplication.service.abstraction;

import com.multiplication.dto.AttemptDTO;
import com.multiplication.model.Attempt;

import java.util.List;

public interface ChallengeService {

    Attempt verifyAttempt(AttemptDTO attemptDTO);
    List<Attempt> getUserStatistics(String userAlias);;
}
