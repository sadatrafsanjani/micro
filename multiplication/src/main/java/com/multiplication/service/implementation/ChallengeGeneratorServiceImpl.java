package com.multiplication.service.implementation;

import com.multiplication.service.abstraction.ChallengeGeneratorService;
import com.multiplication.model.Challenge;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ChallengeGeneratorServiceImpl implements ChallengeGeneratorService {

    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 100;

    private final Random random;

    public ChallengeGeneratorServiceImpl() {
        this.random = new Random();
    }

    public ChallengeGeneratorServiceImpl(final Random random) {
        this.random = random;
    }

    private int next() {
        return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR;
    }

    @Override
    public Challenge randomChallenge() {
        return new Challenge(next(), next());
    }
}
