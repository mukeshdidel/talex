package com.talex.talex.controller;

import com.talex.talex.dto.req.MessagePostRequest;
import com.talex.talex.dto.res.ConversationResponse;
import com.talex.talex.dto.res.MessageResponse;
import com.talex.talex.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conversation")
public class ConversationController {

    public final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<List<ConversationResponse>> getConversations(Principal principal) {
        return ResponseEntity.ok(conversationService.getConversations(principal.getName()));
    }

    @GetMapping("/{conversationId}/message")
    public ResponseEntity<List<MessageResponse>> getMessages(
            Principal principal,
            @PathVariable Long conversationId
    ) {
        return ResponseEntity.ok(conversationService.getMessages(principal.getName(), conversationId));
    }

    @PostMapping("/{conversationId}/message")
    public ResponseEntity<MessageResponse> sendMessage(
            Principal principal,
            @PathVariable Long conversationId,
            @Valid @RequestBody MessagePostRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversationService.sendMessage(principal.getName(), conversationId, request));
    }
}
