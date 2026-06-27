package com.talex.talex.controller;

import com.talex.talex.dto.res.MatchResponse;
import com.talex.talex.dto.res.MatchSuggestResponse;
import com.talex.talex.service.MatchService;
import com.talex.talex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {


    // service
    public final MatchService matchService;


    @GetMapping("/suggestion")
    public ResponseEntity<List<MatchSuggestResponse>> getMatchSuggestions(Principal principal) {
        String username = principal.getName();

        return ResponseEntity.ok().body(matchService.getMatchSuggestion(username));
    }


    @GetMapping
    public ResponseEntity<List<MatchResponse>> getMatches(Principal principal) {
        String username = principal.getName();

        return ResponseEntity.ok().body(matchService.getMatches(username));
    }
}
