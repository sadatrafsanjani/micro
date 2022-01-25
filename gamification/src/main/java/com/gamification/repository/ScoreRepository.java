package com.gamification.repository;

import com.gamification.domain.LeaderBoardRow;
import com.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreCard, Long> {

    @Query("SELECT SUM(S.score) FROM ScoreCard S WHERE S.userId = :userId GROUP BY S.userId")
    Optional<Integer> getTotalScoreForUser(@Param("userId") Long userId);

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);

    @Query("SELECT NEW com.gamification.domain.LeaderBoardRow(S.userId, SUM(S.score)) " +
            "FROM ScoreCard S " +
            "GROUP BY S.userId " +
            "ORDER BY SUM(S.score) DESC")
    List<LeaderBoardRow> findFirst10();
}
