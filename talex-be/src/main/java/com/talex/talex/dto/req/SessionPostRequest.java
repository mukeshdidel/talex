package com.talex.talex.dto.req;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record SessionPostRequest(
        @NotNull(message = "Match id is required")
        Long matchId,

        @NotNull(message = "Scheduled time is required")
        @Future(message = "Scheduled time must be in the future")
        LocalDateTime scheduledAt,

        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be positive")
        Long durationMinutes,

        @Size(max = 500, message = "Meeting link must be 500 characters or less")
        String meetingLink
) {
}
