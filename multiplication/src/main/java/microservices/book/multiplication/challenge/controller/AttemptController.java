package microservices.book.multiplication.challenge.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.challenge.model.Attempt;
import microservices.book.multiplication.challenge.service.abstraction.ChallengeService;
import microservices.book.multiplication.challenge.dto.AttemptDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class AttemptController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<Attempt> postResult(@RequestBody @Valid AttemptDTO attemptDTO) {

        return ResponseEntity.ok(challengeService.verifyAttempt(attemptDTO));
    }
}