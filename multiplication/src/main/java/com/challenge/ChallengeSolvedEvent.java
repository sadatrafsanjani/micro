package com.challenge;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class ChallengeSolvedEvent implements Serializable {

    long attemptId;
    boolean correct;
    int factorA;
    int factorB;
    long userId;
    String userAlias;
}
