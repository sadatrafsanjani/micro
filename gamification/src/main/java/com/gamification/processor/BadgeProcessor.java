package com.gamification.processor;

import com.gamification.domain.BadgeType;
import com.gamification.domain.ScoreCard;
import com.gamification.dto.ChallengeSolvedDTO;
import java.util.List;
import java.util.Optional;

public interface BadgeProcessor {

    Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved);
    BadgeType badgeType();
}
