package com.talex.talex.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record OfferedSkillPostRequest(

        @NotNull(message = "skill id is required")
        Long skillId,

        @NotNull(message = "Proficiency level is required")
        @Min(value = 0, message = "Proficiency level must be at least 0")
        @Max(value = 5, message = "Proficiency level must be at most 5")
        Long proficiencyLevel,

        @PositiveOrZero(message = "Years experience must be zero or positive")
        Long yearsExperience
) {
}
