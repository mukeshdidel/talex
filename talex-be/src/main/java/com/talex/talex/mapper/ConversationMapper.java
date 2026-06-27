package com.talex.talex.mapper;

import com.talex.talex.dto.res.ConversationResponse;
import com.talex.talex.dto.res.MessageResponse;
import com.talex.talex.entity.Conversation;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.Message;
import com.talex.talex.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConversationMapper {

    default boolean isUser1(Match match, User currentUser) {
        return match.getUser1().getId().equals(currentUser.getId());
    }

    @Mapping(target = "conversationId", source = "conversation.id")
    @Mapping(target = "matchId", source = "conversation.match.id")
    @Mapping(target = "otherUserId", expression = "java(isUser1(conversation.getMatch(), currentUser) ? conversation.getMatch().getUser2().getId() : conversation.getMatch().getUser1().getId())")
    @Mapping(target = "otherUsername", expression = "java(isUser1(conversation.getMatch(), currentUser) ? conversation.getMatch().getUser2().getUsername() : conversation.getMatch().getUser1().getUsername())")
    @Mapping(target = "otherName", expression = "java(isUser1(conversation.getMatch(), currentUser) ? conversation.getMatch().getUser2().getName() : conversation.getMatch().getUser1().getName())")
    @Mapping(target = "otherProfilePicture", expression = "java(isUser1(conversation.getMatch(), currentUser) ? conversation.getMatch().getUser2().getProfilePicture() : conversation.getMatch().getUser1().getProfilePicture())")
    @Mapping(target = "createdAt", source = "conversation.createdAt")
    ConversationResponse toConversationResponse(Conversation conversation, @Context User currentUser);

    List<ConversationResponse> toConversationResponseList(List<Conversation> conversations, @Context User currentUser);

    @Mapping(target = "messageId", source = "id")
    @Mapping(target = "conversationId", source = "conversation.id")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderUsername", source = "sender.username")
    @Mapping(target = "senderName", source = "sender.name")
    MessageResponse toMessageResponse(Message message);

    List<MessageResponse> toMessageResponseList(List<Message> messages);
}
