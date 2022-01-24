package microservices.book.multiplication.challenge.service.abstraction;

import microservices.book.multiplication.challenge.dto.AttemptDTO;
import microservices.book.multiplication.challenge.model.Attempt;
import java.util.List;

public interface ChallengeService {

    Attempt verifyAttempt(AttemptDTO attemptDTO);
    List<Attempt> getUserStatistics(String userAlias);;
}
