package microservices.book.multiplication.challenge;

import microservices.book.multiplication.challenge.dto.AttemptDTO;
import microservices.book.multiplication.challenge.model.Attempt;
import microservices.book.multiplication.challenge.service.abstraction.ChallengeService;
import microservices.book.multiplication.challenge.service.implementation.ChallengeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;

public class ChallengeServiceTest {

    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        AttemptDTO attemptDTO = new AttemptDTO(50, 60, "john_doe", 3000);

        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        AttemptDTO attemptDTO = new AttemptDTO(50, 60, "john_doe", 5000);

        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }
}