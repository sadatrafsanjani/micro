package microservices.book.multiplication.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.client.GamificationClient;
import microservices.book.multiplication.dto.AttemptDTO;
import microservices.book.multiplication.model.Attempt;
import microservices.book.multiplication.repository.AttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import microservices.book.multiplication.service.abstraction.ChallengeService;
import microservices.book.multiplication.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;
    private final GamificationClient gamificationClient;

    @Override
    public Attempt verifyAttempt(AttemptDTO dto) {

        User user = userRepository.findByAlias(dto.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", dto.getUserAlias());

                    return userRepository.save(new User(dto.getUserAlias()));
                });

        boolean isCorrect = dto.getGuess() == dto.getFactorA() * dto.getFactorB();

        Attempt attempt = Attempt.builder()
                .id(null)
                .user(user)
                .factorA(dto.getFactorA())
                .factorB(dto.getFactorB())
                .resultAttempt(dto.getGuess())
                .correct(isCorrect)
                .build();

        Attempt storedAttempt = attemptRepository.save(attempt);
        HttpStatus status = gamificationClient.sendAttempt(storedAttempt);
        log.info("Gamification service response: {}", status);

        return storedAttempt;
    }

    @Override
    public List<Attempt> getUserStatistics(String userAlias) {

        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
