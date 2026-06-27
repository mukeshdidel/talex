package com.talex.talex.repo;

import com.talex.talex.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Long> {
    @Query("""
    SELECT DISTINCT m
    FROM Match m
    JOIN FETCH m.user1
    JOIN FETCH m.user2
    JOIN FETCH m.user1SkillOffered u1so
    JOIN FETCH u1so.skill
    JOIN FETCH m.user2SkillOffered u2so
    JOIN FETCH u2so.skill
    WHERE
        (m.user1.id = :userId OR m.user2.id = :userId)
    AND m.status = com.talex.talex.entity.MatchStatus.ACTIVE
    """)
    List<Match> findMatchesByUserId(Long userId);
}
