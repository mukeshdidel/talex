package com.talex.talex.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record AvailabilityPostRequest(
        @NotNull(message = "dayOfWeek is required")
        @Min(value = 1, message = "dayOfWeek must be between 1 and 7")
        @Max(value = 7, message = "dayOfWeek must be between 1 and 7")
        Long dayOfWeek,

        @NotNull(message = "startTime is required")
        LocalTime startTime,

        @NotNull(message = "endTime is required")
        LocalTime endTime
) {
}
