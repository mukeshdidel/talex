package com.talex.talex.dto.res;


import lombok.Builder;

@Builder
public record WantedSkillPostResponse(
        Long id,
        Long skillId,
        String skillName,
        Long priority
) {
}
