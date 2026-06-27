package com.talex.talex.dto.res;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long notificationId,
        String title,
        String message,
        Boolean isRead,
        LocalDateTime createdAt
) {
}
