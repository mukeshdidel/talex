package com.talex.talex.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OfferedSkillPostRequest(

        @NotNull(message = "skill id is required")
        Long skillId,

        @NotNull(message = "Proficiency level is required")
        @Min(value = 0, message = "Proficiency level must be at least 0")
        @Max(value = 5, message = "Proficiency level must be at most 5")
        Long proficiencyLevel,

        Long yearsExperience
) {
}
