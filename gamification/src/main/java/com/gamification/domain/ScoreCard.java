package com.gamification.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scorecards")
public class ScoreCard {

    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;

    private Long userId;
    private Long attemptId;

    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;
    private int score;

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
