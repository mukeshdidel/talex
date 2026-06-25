package com.talex.talex.dto.res;

import com.talex.talex.entity.UserSkillOffered;
import com.talex.talex.entity.UserSkillWanted;
import lombok.Builder;

import java.util.Set;

@Builder
public record MeResponse(
        String name,
        String username,
        String email,
        String bio,
        String profilePicture,
        Set<OfferedSkillPostResponse> skillsOffered,
        Set<WantedSkillPostResponse> skillsWanted
) {
}
