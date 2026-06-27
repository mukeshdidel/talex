package com.talex.talex.dto.res;

import java.time.LocalDateTime;

public record MessageResponse(
        Long messageId,
        Long conversationId,
        Long senderId,
        String senderUsername,
        String senderName,
        String text,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
