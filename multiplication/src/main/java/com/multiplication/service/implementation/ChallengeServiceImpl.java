package com.multiplication.service.implementation;

import com.multiplication.dto.AttemptDTO;
import com.multiplication.model.Attempt;
import com.multiplication.model.User;
import com.multiplication.publisher.ChallengeEventPublisher;
import com.multiplication.repository.AttemptRepository;
import com.multiplication.repository.UserRepository;
import com.multiplication.service.abstraction.ChallengeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;
    private final ChallengeEventPublisher challengeEventPublisher;

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
        challengeEventPublisher.publishChallenge(storedAttempt);
        log.info("Event published");

        return storedAttempt;
    }

    @Override
    public List<Attempt> getUserStatistics(String userAlias) {

        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
