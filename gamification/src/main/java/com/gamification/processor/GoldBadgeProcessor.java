package com.gamification.processor;

import com.gamification.domain.BadgeType;
import com.gamification.domain.ScoreCard;
import com.gamification.dto.ChallengeSolvedDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


@Component
class GoldBadgeProcessor implements BadgeProcessor {

    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {

        return currentScore > 400 ? Optional.of(BadgeType.GOLD) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {

        return BadgeType.GOLD;
    }
}