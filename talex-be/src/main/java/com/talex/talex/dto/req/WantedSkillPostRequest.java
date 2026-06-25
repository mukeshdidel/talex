package com.talex.talex.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record WantedSkillPostRequest(
        @NotNull(message = "skill id is required")
        Long skillId,



        @NotNull(message = "Priority is required")
        @Min(value = 0, message = "Priority must be at least 0")
        @Max(value = 5, message = "Priority must be at most 5")
        Long priority

        ) {
}
