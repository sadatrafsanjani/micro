package com.multiplication.repository;

import com.multiplication.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    List<Attempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);

    @Query("SELECT A FROM Attempt A WHERE A.user.alias = ?1 ORDER BY A.id DESC")
    List<Attempt> lastAttempts(String userAlias);
}
