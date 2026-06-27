package com.talex.talex.dto.res;

import java.util.Set;

public record UserSearchResponse(
        Long userId,
        String username,
        String name,
        String bio,
        String profilePicture,
        Set<OfferedSkillPostResponse> skillsOffered,
        Set<WantedSkillPostResponse> skillsWanted,
        Set<AvailabilityPostResponse> availabilities
) {
}
