package com.gamification.processor;

import com.gamification.domain.BadgeType;
import com.gamification.domain.ScoreCard;
import com.gamification.dto.ChallengeSolvedDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
class SilverBadgeProcessor implements BadgeProcessor {

  @Override
  public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {

    return currentScore > 150 ? Optional.of(BadgeType.SILVER) : Optional.empty();
  }

  @Override
  public BadgeType badgeType() {

    return BadgeType.SILVER;
  }
}