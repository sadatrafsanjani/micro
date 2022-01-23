package microservices.book.multiplication.challenge.service.implementation;

import microservices.book.multiplication.challenge.dto.AttemptDTO;
import microservices.book.multiplication.challenge.model.Attempt;
import microservices.book.multiplication.challenge.service.abstraction.ChallengeService;
import microservices.book.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Override
    public Attempt verifyAttempt(AttemptDTO attemptDTO) {

        // Check if the attempt is correct
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        // We don't use identifiers for now
        User user = new User(null, attemptDTO.getUserAlias());

        // Builds the domain object. Null id for now.

        return Attempt.builder()
                .id(null)
                .user(user)
                .factorA(attemptDTO.getFactorA())
                .factorB(attemptDTO.getFactorB())
                .resultAttempt(attemptDTO.getGuess())
                .correct(isCorrect)
                .build();
    }
}
