package com.talex.talex.mapper;

import com.talex.talex.dto.res.SessionResponse;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.Session;
import com.talex.talex.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionMapper {

    default boolean isUser1(Match match, User currentUser) {
        return match.getUser1().getId().equals(currentUser.getId());
    }

    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "matchId", source = "session.match.id")
    @Mapping(target = "hostId", source = "session.host.id")
    @Mapping(target = "hostUsername", source = "session.host.username")
    @Mapping(target = "hostName", source = "session.host.name")
    @Mapping(target = "otherUserId", expression = "java(isUser1(session.getMatch(), currentUser) ? session.getMatch().getUser2().getId() : session.getMatch().getUser1().getId())")
    @Mapping(target = "otherUsername", expression = "java(isUser1(session.getMatch(), currentUser) ? session.getMatch().getUser2().getUsername() : session.getMatch().getUser1().getUsername())")
    @Mapping(target = "otherName", expression = "java(isUser1(session.getMatch(), currentUser) ? session.getMatch().getUser2().getName() : session.getMatch().getUser1().getName())")
    SessionResponse toSessionResponse(Session session, @Context User currentUser);

    List<SessionResponse> toSessionResponseList(List<Session> sessions, @Context User currentUser);
}
