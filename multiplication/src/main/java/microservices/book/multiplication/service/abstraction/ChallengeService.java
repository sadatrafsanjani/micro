package microservices.book.multiplication.service.abstraction;

import microservices.book.multiplication.dto.AttemptDTO;
import microservices.book.multiplication.model.Attempt;
import java.util.List;

public interface ChallengeService {

    Attempt verifyAttempt(AttemptDTO attemptDTO);
    List<Attempt> getUserStatistics(String userAlias);;
}
