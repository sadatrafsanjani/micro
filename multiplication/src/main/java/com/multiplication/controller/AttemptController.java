package com.multiplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.multiplication.model.Attempt;
import com.multiplication.service.abstraction.ChallengeService;
import com.multiplication.dto.AttemptDTO;
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

        log.info("Received new attempt from {}", attemptDTO.getUserAlias());

        return ResponseEntity.ok(challengeService.verifyAttempt(attemptDTO));
    }

    @GetMapping
    public ResponseEntity<List<Attempt>> getStatistics(@RequestParam("alias") String alias) {

        return ResponseEntity.ok(challengeService.getUserStatistics(alias));
    }
}