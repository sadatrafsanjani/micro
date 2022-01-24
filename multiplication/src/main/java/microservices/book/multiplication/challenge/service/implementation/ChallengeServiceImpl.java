package microservices.book.multiplication.challenge.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.challenge.dto.AttemptDTO;
import microservices.book.multiplication.challenge.model.Attempt;
import microservices.book.multiplication.challenge.repository.AttemptRepository;
import microservices.book.multiplication.challenge.repository.UserRepository;
import microservices.book.multiplication.challenge.service.abstraction.ChallengeService;
import microservices.book.multiplication.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private UserRepository userRepository;
    private AttemptRepository attemptRepository;

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

        return attemptRepository.save(attempt);
    }

    @Override
    public List<Attempt> getUserStatistics(String userAlias) {

        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
