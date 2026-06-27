package com.talex.talex.service;


import com.talex.talex.dto.req.MessagePostRequest;
import com.talex.talex.dto.res.ConversationResponse;
import com.talex.talex.dto.res.MessageResponse;
import com.talex.talex.entity.Conversation;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.Message;
import com.talex.talex.entity.User;
import com.talex.talex.mapper.ConversationMapper;
import com.talex.talex.repo.ConversationRepo;
import com.talex.talex.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepo conversationRepo;
    private final MessageRepo messageRepo;
    private final UserService userService;
    private final NotificationService notificationService;
    private final ConversationMapper conversationMapper;

    @Transactional
    public Conversation createConversation(Match match) {

        Conversation conversation = Conversation.builder()
                .match(match)
                .build();

        return conversationRepo.save(conversation);
    }

    @Transactional(readOnly = true)
    public List<ConversationResponse> getConversations(String username) {
        User currentUser = userService.getUserByUsername(username);
        List<Conversation> conversations = conversationRepo.findConversationsByUserId(currentUser.getId());

        return conversationMapper.toConversationResponseList(conversations, currentUser);
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> getMessages(String username, Long conversationId) {
        User currentUser = userService.getUserByUsername(username);
        Conversation conversation = getConversationForUser(conversationId, currentUser);
        List<Message> messages = messageRepo.findByConversation_IdOrderByCreatedAtAsc(conversation.getId());

        return conversationMapper.toMessageResponseList(messages);
    }

    @Transactional
    public MessageResponse sendMessage(String username, Long conversationId, MessagePostRequest request) {
        User currentUser = userService.getUserByUsername(username);
        Conversation conversation = getConversationForUser(conversationId, currentUser);

        Message message = Message.builder()
                .conversation(conversation)
                .sender(currentUser)
                .text(request.text().trim())
                .build();

        message = messageRepo.save(message);

        User receiver = getOtherParticipant(conversation.getMatch(), currentUser);
        notificationService.notify(
                receiver,
                "New message",
                currentUser.getName() + " sent you a message."
        );

        return conversationMapper.toMessageResponse(message);
    }

    private Conversation getConversationForUser(Long conversationId, User currentUser) {
        Conversation conversation = conversationRepo.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Conversation not found"
                ));

        boolean isParticipant = conversation.getMatch().getUser1().getId().equals(currentUser.getId())
                || conversation.getMatch().getUser2().getId().equals(currentUser.getId());

        if (!isParticipant) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to access this conversation"
            );
        }

        return conversation;
    }

    private User getOtherParticipant(Match match, User currentUser) {
        if (match.getUser1().getId().equals(currentUser.getId())) {
            return match.getUser2();
        }

        return match.getUser1();
    }

}
