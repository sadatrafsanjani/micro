package microservices.book.multiplication.challenge.service.abstraction;

import microservices.book.multiplication.challenge.dto.AttemptDTO;
import microservices.book.multiplication.challenge.model.Attempt;

public interface ChallengeService {

    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     *
     * @return the resulting ChallengeAttempt object
     */
    Attempt verifyAttempt(AttemptDTO attemptDTO);

}
