package com.talex.talex.service;


import com.talex.talex.entity.Conversation;
import com.talex.talex.entity.Match;
import com.talex.talex.repo.ConversationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepo conversationRepo;

    @Transactional
    public Conversation createConversation(Match match) {

        Conversation conversation = Conversation.builder()
                .match(match)
                .build();

        return conversationRepo.save(conversation);
    }

}
