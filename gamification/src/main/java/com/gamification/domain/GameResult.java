package com.gamification.domain;

import lombok.*;
import java.util.List;

@Value
public class GameResult {

    private int score;
    private List<BadgeType> badges;
}
