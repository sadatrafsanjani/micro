package microservices.book.multiplication.challenge;

import microservices.book.multiplication.challenge.dto.AttemptDTO;
import microservices.book.multiplication.challenge.model.Attempt;
import microservices.book.multiplication.challenge.repository.AttemptRepository;
import microservices.book.multiplication.challenge.repository.UserRepository;
import microservices.book.multiplication.challenge.service.abstraction.ChallengeService;
import microservices.book.multiplication.challenge.service.implementation.ChallengeServiceImpl;
import microservices.book.multiplication.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

    private ChallengeService challengeService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AttemptRepository attemptRepository;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(userRepository, attemptRepository);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        AttemptDTO attemptDTO = new AttemptDTO(50, 60, "john_doe", 3000);

        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();

        verify(userRepository).save(new User("john_doe"));
        verify(attemptRepository).save(resultAttempt);
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