package com.talex.talex.service;

import com.talex.talex.dto.req.ExchangeRequestPostRequest;
import com.talex.talex.dto.res.ExchangeRequestIncomingResponse;
import com.talex.talex.dto.res.ExchangeRequestOutgoingResponse;
import com.talex.talex.dto.res.ExchangeRequestPostResponse;
import com.talex.talex.entity.*;
import com.talex.talex.mapper.ExchangeRequestMapper;
import com.talex.talex.repo.ExchangeRequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRequestService {

    // service
    public final ConversationService conversationService;
    public final MatchService matchService;
    public final NotificationService notificationService;
    public final SkillService skillService;
    public final UserService userService;

    // repo
    public final ExchangeRequestRepo exchangeRequestRepo;

    // mapper
    public final ExchangeRequestMapper exchangeRequestMapper;



    public ExchangeRequestPostResponse createExchangeRequest(String username, ExchangeRequestPostRequest request) {

        User sender = userService.getUserByUsername(username);
        User receiver = userService.getUserById(request.receiverId());

        if (sender.getId().equals(receiver.getId())) {
            throw new IllegalArgumentException("You cannot send an exchange request to yourself.");
        }

        if (exchangeRequestRepo.existsPendingBetweenUsers(
                sender.getId(),
                receiver.getId(),
                ExchangeRequestStatus.PENDING
        )) {
            throw new IllegalStateException("A pending exchange request already exists between these users.");
        }

        UserSkillOffered senderSkillOffered = skillService.getSkillOfferedForUser(
                request.senderSkillOfferId(),
                sender
        );
        UserSkillOffered receiverSkillOffered = skillService.getSkillOfferedForUser(
                request.receiverSkillOfferId(),
                receiver
        );

        ExchangeRequest exchangeRequest = exchangeRequestMapper.toExchangeRequest(request);

        exchangeRequest.setSender(sender);
        exchangeRequest.setReceiver(receiver);
        exchangeRequest.setSenderSkillOffered(senderSkillOffered);
        exchangeRequest.setReceiverSkillOffered(receiverSkillOffered);

        exchangeRequestRepo.save(exchangeRequest);

        notificationService.notify(
                receiver,
                "New exchange request",
                sender.getName() + " sent you a skill exchange request."
        );

        return exchangeRequestMapper.toExchangeRequestPostResponse(exchangeRequest);


    }

    public List<ExchangeRequestIncomingResponse> getIncomingExchangeRequest(String username) {

        User user = userService.getUserByUsername(username);
        List<ExchangeRequest> incomingRequests = exchangeRequestRepo.findByReceiver(user);
        return exchangeRequestMapper.toExchangeRequestIncomingResponseList(incomingRequests);

    }

    public List<ExchangeRequestOutgoingResponse> getOutgoingExchangeRequest(String username) {
        User user = userService.getUserByUsername(username);
        List<ExchangeRequest> outgoingRequests = exchangeRequestRepo.findBySender(user);
        return exchangeRequestMapper.toExchangeRequestOutgoingResponseList(outgoingRequests);
    }


    @Transactional
    public void acceptExchangeRequest(String username, Long requestId) {
        User user = userService.getUserByUsername(username);
        ExchangeRequest request = exchangeRequestRepo.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exchange request not found"
                ));

        if (!request.getReceiver().getId().equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to accept this request."
            );
        }

        // Only pending requests can be accepted
        if (request.getStatus() != ExchangeRequestStatus.PENDING) {
            throw new IllegalStateException("Request has already been processed.");
        }

        request.setStatus(ExchangeRequestStatus.ACCEPTED);
        exchangeRequestRepo.save(request);

        // create match
        Match match = matchService.createMatch(request);
        conversationService.createConversation(match);

        notificationService.notify(
                request.getSender(),
                "Exchange request accepted",
                request.getReceiver().getName() + " accepted your skill exchange request."
        );

    }

    public void rejectExchangeRequest(String username, Long requestId) {

        User user = userService.getUserByUsername(username);
        ExchangeRequest request = exchangeRequestRepo.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exchange request not found"
                ));

        if (!request.getReceiver().getId().equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to reject this request."
            );
        }

        // Only pending requests can be rejected
        if (request.getStatus() != ExchangeRequestStatus.PENDING) {
            throw new IllegalStateException("Request has already been processed.");
        }

        request.setStatus(ExchangeRequestStatus.REJECTED);
        exchangeRequestRepo.save(request);

        notificationService.notify(
                request.getSender(),
                "Exchange request rejected",
                request.getReceiver().getName() + " rejected your skill exchange request."
        );
    }
}
