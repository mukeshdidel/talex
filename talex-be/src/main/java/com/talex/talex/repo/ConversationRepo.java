package com.talex.talex.repo;

import com.talex.talex.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepo extends JpaRepository<Conversation, Long> {

    @Query("""
    SELECT DISTINCT c
    FROM Conversation c
    JOIN FETCH c.match m
    JOIN FETCH m.user1
    JOIN FETCH m.user2
    WHERE m.user1.id = :userId OR m.user2.id = :userId
    ORDER BY c.createdAt DESC
    """)
    List<Conversation> findConversationsByUserId(Long userId);
}
