package com.gamification.controller;

import com.gamification.domain.LeaderBoardRow;
import com.gamification.service.abstraction.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/leaderboards")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderBoardService leaderBoardService;

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {

        return leaderBoardService.getCurrentLeaderBoard();
    }
}
