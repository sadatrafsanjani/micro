package microservices.book.multiplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.model.Challenge;
import microservices.book.multiplication.service.abstraction.ChallengeGeneratorService;
import org.springframework.web.bind.annotation.*;

/**
 * This class implements a REST API to get random challenges
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private final ChallengeGeneratorService challengeGeneratorService;

    @GetMapping("/random")
    Challenge getRandomChallenge() {

        Challenge challenge = challengeGeneratorService.randomChallenge();

        return challenge;
    }
}
