package com.talex.talex.mapper;


import com.talex.talex.dto.req.ExchangeRequestPostRequest;
import com.talex.talex.dto.res.ExchangeRequestIncomingResponse;
import com.talex.talex.dto.res.ExchangeRequestOutgoingResponse;
import com.talex.talex.dto.res.ExchangeRequestPostResponse;
import com.talex.talex.entity.ExchangeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface ExchangeRequestMapper {



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    @Mapping(target = "senderSkillOffered", ignore = true)
    @Mapping(target = "receiverSkillOffered", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "createdAt", ignore = true)
    ExchangeRequest toExchangeRequest(ExchangeRequestPostRequest request);


    @Mapping(target = "requestId", source = "id")
    @Mapping(target = "status", source = "status")
    ExchangeRequestPostResponse toExchangeRequestPostResponse(ExchangeRequest entity);

    List<ExchangeRequestPostResponse> toResponseList(List<ExchangeRequest> list);

    @Mapping(target = "requestId", source = "id")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderUsername", source = "sender.username")
    @Mapping(target = "senderName", source = "sender.name")
    @Mapping(target = "senderSkillOffered", source = "senderSkillOffered")
    @Mapping(target = "receiverSkillOffered", source = "receiverSkillOffered")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "status", source = "status")
    ExchangeRequestIncomingResponse toExchangeRequestIncomingResponse(ExchangeRequest exchangeRequest);

    List<ExchangeRequestIncomingResponse> toExchangeRequestIncomingResponseList(List<ExchangeRequest> requests);

    @Mapping(target = "requestId", source = "id")
    @Mapping(target = "receiverId", source = "receiver.id")
    @Mapping(target = "receiverUsername", source = "receiver.username")
    @Mapping(target = "receiverName", source = "receiver.name")
    @Mapping(target = "senderSkillOffered", source = "senderSkillOffered")
    @Mapping(target = "receiverSkillOffered", source = "receiverSkillOffered")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "status", source = "status")
    ExchangeRequestOutgoingResponse toExchangeRequestOutgoingResponse(ExchangeRequest exchangeRequest);
    List<ExchangeRequestOutgoingResponse> toExchangeRequestOutgoingResponseList(List<ExchangeRequest> outgoingRequests);


}
