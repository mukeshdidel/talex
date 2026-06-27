package com.talex.talex.dto.res;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        Long sessionId,
        Long reviewerId,
        String reviewerUsername,
        Long reviewedUserId,
        String reviewedUsername,
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {
}
