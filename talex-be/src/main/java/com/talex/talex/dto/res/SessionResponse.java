package com.talex.talex.dto.res;

import com.talex.talex.entity.SessionStatus;

import java.time.LocalDateTime;

public record SessionResponse(
        Long sessionId,
        Long matchId,
        Long hostId,
        String hostUsername,
        String hostName,
        Long otherUserId,
        String otherUsername,
        String otherName,
        LocalDateTime scheduledAt,
        Long durationMinutes,
        String meetingLink,
        SessionStatus status
) {
}
