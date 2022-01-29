package com.challenge;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChallengeSolvedEvent {

    long attemptId;
    boolean correct;
    int factorA;
    int factorB;
    long userId;
    String userAlias;
}
