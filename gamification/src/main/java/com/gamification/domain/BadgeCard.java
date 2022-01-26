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
@Table(name = "badgecards")
public class BadgeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @Column(name = "USER_ID")
    private Long userId;

    @EqualsAndHashCode.Exclude
    @Column(name = "BADGE_TIMESTAMP")
    private long badgeTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "BADGE_TYPE")
    private BadgeType badgeType;

    public BadgeCard(final Long userId, final BadgeType badgeType) {
        this(null, userId, System.currentTimeMillis(), badgeType);
    }
}
