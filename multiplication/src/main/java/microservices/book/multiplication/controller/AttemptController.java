package microservices.book.multiplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.model.Attempt;
import microservices.book.multiplication.service.abstraction.ChallengeService;
import microservices.book.multiplication.dto.AttemptDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Attempt>> getStatistics(@RequestParam("alias") String alias) {

        return ResponseEntity.ok(challengeService.getUserStatistics(alias));
    }
}