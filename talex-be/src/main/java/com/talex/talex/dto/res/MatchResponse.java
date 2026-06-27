package com.talex.talex.dto.res;

import com.talex.talex.entity.MatchStatus;

public record MatchResponse(
        Long matchId,
        Long otherUserId,
        String otherUsername,
        String otherName,
        String otherProfilePicture,
        String theyTeach,
        Long proficiencyLevel,
        Long yearsExperience,
        String iTeach,
        MatchStatus status
) {
}
