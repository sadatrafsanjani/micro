package com.gamification.service;

import com.gamification.domain.LeaderBoardRow;
import com.gamification.repository.BadgeRepository;
import com.gamification.repository.ScoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        // Get score only
        List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();
        // Combine with badges
        return scoreOnly.stream().map(row -> {
            List<String> badges =
                    badgeRepository.findByUserIdOrderByBadgeTimestampDesc(
                                    row.getUserId()).stream()
                            .map(b ->
                                b.getBadgeType().getDescription()
                            )
                            .collect(Collectors.toList());
            return row.withBadges(badges);
        }).collect(Collectors.toList());
    }
}
