package microservices.book.multiplication.challenge;

import microservices.book.multiplication.client.GamificationClient;
import microservices.book.multiplication.dto.AttemptDTO;
import microservices.book.multiplication.model.Attempt;
import microservices.book.multiplication.repository.AttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import microservices.book.multiplication.service.abstraction.ChallengeService;
import microservices.book.multiplication.service.implementation.ChallengeServiceImpl;
import microservices.book.multiplication.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

    private ChallengeService challengeService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AttemptRepository attemptRepository;
    @Mock
    private GamificationClient gamificationClient;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(userRepository, attemptRepository, gamificationClient);
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
        verify(gamificationClient).sendAttempt(resultAttempt);
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