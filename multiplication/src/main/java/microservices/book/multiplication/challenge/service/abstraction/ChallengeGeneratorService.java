package microservices.book.multiplication.challenge.service.abstraction;

import microservices.book.multiplication.challenge.model.Challenge;

public interface ChallengeGeneratorService {

    /**
     * @return a randomly-generated challenge with factors between 11 and 99
     */
    Challenge randomChallenge();

}