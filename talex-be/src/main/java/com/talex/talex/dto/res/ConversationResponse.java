package com.talex.talex.dto.res;

import java.time.LocalTime;

public record ConversationResponse(
        Long conversationId,
        Long matchId,
        Long otherUserId,
        String otherUsername,
        String otherName,
        String otherProfilePicture,
        LocalTime createdAt
) {
}
