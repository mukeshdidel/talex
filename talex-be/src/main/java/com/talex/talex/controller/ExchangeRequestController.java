package com.talex.talex.controller;

import com.talex.talex.dto.req.ExchangeRequestPostRequest;
import com.talex.talex.dto.res.ExchangeRequestIncomingResponse;
import com.talex.talex.dto.res.ExchangeRequestOutgoingResponse;
import com.talex.talex.dto.res.ExchangeRequestPostResponse;
import com.talex.talex.service.ExchangeRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/exchange-request")
@RequiredArgsConstructor
public class ExchangeRequestController {


    // service
    public final ExchangeRequestService exchangeRequestService;

    @PostMapping
    public ResponseEntity<ExchangeRequestPostResponse> createExchangeRequest(@Valid @RequestBody ExchangeRequestPostRequest request, Principal principal) {
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeRequestService.createExchangeRequest(username, request));
    }

    @GetMapping("/incoming")
    public ResponseEntity<List<ExchangeRequestIncomingResponse>> getIncomingExchangeRequest(Principal principal) {
        return ResponseEntity.ok().body(exchangeRequestService.getIncomingExchangeRequest(principal.getName()));
    }

    @GetMapping("/outgoing")
    public ResponseEntity<List<ExchangeRequestOutgoingResponse>> getOutgoingExchangeRequest(Principal principal) {
        return ResponseEntity.ok().body(exchangeRequestService.getOutgoingExchangeRequest(principal.getName()));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptExchangeRequest(Principal principal, @PathVariable("id") Long requestId) {
        String username = principal.getName();
        exchangeRequestService.acceptExchangeRequest(username, requestId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectExchangeRequest(Principal principal, @PathVariable("id") Long requestId) {
        String username = principal.getName();
        exchangeRequestService.rejectExchangeRequest(username, requestId);
        return ResponseEntity.noContent().build();
    }

}
