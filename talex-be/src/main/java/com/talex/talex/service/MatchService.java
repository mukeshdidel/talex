package com.talex.talex.service;


import com.talex.talex.dto.res.MatchSuggestResponse;
import com.talex.talex.entity.ExchangeRequest;
import com.talex.talex.entity.Match;
import com.talex.talex.entity.MatchStatus;
import com.talex.talex.entity.User;
import com.talex.talex.mapper.MatchMapper;
import com.talex.talex.repo.MatchRepo;
import com.talex.talex.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    // service
    public final UserService userService;

    // repo
    public final UserRepo userRepo;
    public final MatchRepo matchRepo;

    // mapper
    public final MatchMapper matchMapper;

    public List<MatchSuggestResponse> getMatchSuggestion(String username) {

        User user = userService.getUserByUsername(username);

        System.out.println("user: "+ user.getId());

        List<User> allUsers = userRepo.findMatchSuggestions(user.getId());

        return matchMapper.toMatchSuggestResponseList(allUsers);
    }


    public Match createMatch(ExchangeRequest request) {
        Match match = Match.builder()
                .user1(request.getSender())
                .user2(request.getReceiver())
                .user1SkillOffered(request.getSenderSkillOffered())
                .user2SkillOffered(request.getReceiverSkillOffered())
                .status(MatchStatus.ACTIVE)
                .build();

        return matchRepo.save(match);

    }
}
