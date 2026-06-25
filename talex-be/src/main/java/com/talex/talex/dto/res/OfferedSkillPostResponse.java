package com.talex.talex.dto.res;


import lombok.Builder;

@Builder
public record OfferedSkillPostResponse(
        Long id,
        Long skillId,
        String skillName,
        Long proficiencyLevel,
        Long yearsExperience

) {
}
