package com.talex.talex.repo;

import com.talex.talex.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepo extends JpaRepository<Conversation, Long> {
}
