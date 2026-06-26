package com.talex.talex.dto.res;

import com.talex.talex.entity.UserSkillOffered;
import com.talex.talex.entity.UserSkillWanted;

import java.util.Set;

public record MatchSuggestResponse(
        Long userId,
        String username,
        String name,
        Set<OfferedSkillPostResponse> teaches,
        Set<WantedSkillPostResponse> wants
) {
}
