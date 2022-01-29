package com.gamification.controller;

import com.challenge.ChallengeSolvedEvent;
import com.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<?> postResult(@RequestBody ChallengeSolvedEvent dto) {

        gameService.newAttemptForUser(dto);

        return ResponseEntity.ok().build();
    }
}
