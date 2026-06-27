package com.talex.talex.controller;

import com.talex.talex.dto.req.SessionPostRequest;
import com.talex.talex.dto.res.SessionResponse;
import com.talex.talex.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionController {

    public final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponse> createSession(
            Principal principal,
            @Valid @RequestBody SessionPostRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessionService.createSession(principal.getName(), request));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions(Principal principal) {
        return ResponseEntity.ok(sessionService.getSessions(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getSession(
            Principal principal,
            @PathVariable("id") Long sessionId
    ) {
        return ResponseEntity.ok(sessionService.getSession(principal.getName(), sessionId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<SessionResponse> cancelSession(
            Principal principal,
            @PathVariable("id") Long sessionId
    ) {
        return ResponseEntity.ok(sessionService.cancelSession(principal.getName(), sessionId));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<SessionResponse> completeSession(
            Principal principal,
            @PathVariable("id") Long sessionId
    ) {
        return ResponseEntity.ok(sessionService.completeSession(principal.getName(), sessionId));
    }
}
