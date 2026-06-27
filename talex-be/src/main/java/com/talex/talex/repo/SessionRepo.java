package com.talex.talex.repo;

import com.talex.talex.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepo extends JpaRepository<Session, Long> {

    @Query("""
    SELECT DISTINCT s
    FROM Session s
    JOIN FETCH s.match m
    JOIN FETCH s.host
    JOIN FETCH m.user1
    JOIN FETCH m.user2
    WHERE m.user1.id = :userId OR m.user2.id = :userId
    ORDER BY s.scheduledAt DESC
    """)
    List<Session> findSessionsByUserId(Long userId);
}
